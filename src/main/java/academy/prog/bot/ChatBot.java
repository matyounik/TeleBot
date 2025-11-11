package academy.prog.bot;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import academy.prog.model.User;
import academy.prog.service.UserService;
import academy.prog.mail.NotificationService;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LogManager.getLogger(ChatBot.class);

    private static final String BROADCAST = "broadcast ";
    private static final String LIST_USERS = "users";

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    public ChatBot(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText())
            return;

        final String text = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();

        User user = userService.findByChatId(chatId);

        if (checkIfAdminCommand(user, text))
            return;

        BotContext context;
        BotState state;

        if (user == null) {
            state = BotState.getInitialState();
            user = new User(chatId, state.ordinal());
            userService.addUser(user);

            context = BotContext.of(this, user, text);
            state.enter(context);

            LOGGER.info("New user registered: " + chatId);
        } else {
            context = BotContext.of(this, user, text);
            state = BotState.byId(user.getStateId());

            LOGGER.info("Update received for user in state: " + state);
        }

        state.handleInput(context);

        do {
            state = state.nextState();
            state.enter(context);
        } while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        userService.updateUser(user);
    }

    private boolean checkIfAdminCommand(User user, String text) {
        if (user == null || !Boolean.TRUE.equals(user.getAdmin()))
            return false;

        if (text.startsWith(BROADCAST)) {
            text = text.substring(BROADCAST.length());
            broadcast(text);
            return true;

        } else if (text.equals(LIST_USERS)) {
            listUsers(user);
            return true;

        } else if (text.startsWith("sendEmail ")) {
            String[] parts = text.split(" ", 3);
            if (parts.length < 3) {
                sendMessage(user.getChatId(), "Format: sendEmail <userId> <message>");
                return true;
            }

            Long userId = Long.parseLong(parts[1]);
            String msg = parts[2];
            sendEmailToUser(userId, msg);
            return true;
        }

        return false;
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(Long.toString(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(long chatId) {
        InputStream is = getClass().getClassLoader().getResourceAsStream("test.png");

        SendPhoto message = new SendPhoto();
        message.setChatId(Long.toString(chatId));
        message.setPhoto(new InputFile(is, "test"));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void listUsers(User admin) {
        StringBuilder sb = new StringBuilder("All users list:\r\n");
        List<User> users = userService.findAllUsers();

        users.forEach(user ->
                sb.append(user.getId())
                        .append(' ')
                        .append(user.getPhone())
                        .append(' ')
                        .append(user.getEmail())
                        .append("\r\n")
        );

        sendPhoto(admin.getChatId());
        sendMessage(admin.getChatId(), sb.toString());
    }

    private void broadcast(String text) {
        int page = 0;
        int size = 500;
        List<User> users;

        do {
            users = userService.findUsersPage(page, size);
            users.forEach(u -> sendMessage(u.getChatId(), text));
            page++;
        } while (!users.isEmpty());
    }

    private void sendEmailToUser(Long userId, String text) {
        User target = userService.findById(userId);
        if (target == null || target.getEmail() == null)
            return;

        notificationService.sendEmailToUser(target.getEmail(), text);
    }
}