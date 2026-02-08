package academy.prog.bot;

import academy.prog.model.Role;
import academy.prog.model.User;
import academy.prog.repo.RoleRepository;
import academy.prog.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
@RequiredArgsConstructor
public class ChatBot extends TelegramLongPollingBot {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;

    private final UserRepository repo;
    private final BotContext ctx;
    private final ScheduleService scheduleService;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.bot.name}")
    private String name;

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        User user = repo.findByChatId(chatId).orElseGet(() -> {
            User u = new User();
            u.setChatId(chatId);
            u.setRegistered(false);
            u.setLoggedIn(false);
            return repo.save(u);
        });

        /* LANGUAGE */

        if (user.getLang() == null) {
            if (text.equals("🇬🇧 English")) user.setLang("en");
            else if (text.equals("🇪🇸 Español")) user.setLang("es");
            else if (text.equals("🇺🇦 Українська")) user.setLang("ua");
            else {
                send(chatId, "🌍 Choose language", Keyboards.language());
                return;
            }
            repo.save(user);
            ctx.setState(user, BotState.START);
            sendMenu(chatId, user);
            return;
        }

        String lang = user.getLang();

        /* ================= GLOBAL ================= */

        if (text.equals(I18n.t("btn_back", lang))) {
            ctx.setState(user, BotState.START);
            sendMenu(chatId, user);
            return;
        }

        if (text.equals(I18n.t("btn_logout", lang))) {
            user.setLoggedIn(false);
            repo.save(user);
            ctx.clearState(user);
            sendMenu(chatId, user);
            return;
        }

        if (text.equals(I18n.t("btn_settings", lang))) {
            send(chatId, I18n.t("settings", lang), Keyboards.settings(lang));
            return;
        }

        if (text.equals(I18n.t("btn_language", lang))) {
            user.setLang(null);
            repo.save(user);
            send(chatId, "🌍 Choose language", Keyboards.language());
            return;
        }

        /*PROFILE BUTTONS */

        if (text.equals(I18n.t("btn_edit_name", lang))) {
            ctx.setState(user, BotState.EDIT_NAME);
            send(chatId, I18n.t("enter_name", lang), Keyboards.remove());
            return;
        }

        if (text.equals(I18n.t("btn_edit_phone", lang))) {
            ctx.setState(user, BotState.EDIT_PHONE);
            send(chatId, I18n.t("enter_phone", lang), Keyboards.remove());
            return;
        }

        if (text.equals(I18n.t("btn_edit_email", lang))) {
            ctx.setState(user, BotState.EDIT_EMAIL);
            send(chatId, I18n.t("enter_email", lang), Keyboards.remove());
            return;
        }

        if (text.equals(I18n.t("btn_edit_class", lang))) {
            ctx.setState(user, BotState.EDIT_CLASS);
            send(chatId, I18n.t("enter_class", lang), Keyboards.classSelect());
            return;
        }

        if (text.equals(I18n.t("btn_change_password", lang))) {
            ctx.setState(user, BotState.EDIT_PASSWORD_OLD);
            send(chatId, I18n.t("enter_password", lang), Keyboards.remove());
            return;
        }

        /*FSM */

        switch (ctx.getState(user)) {

            /* START */

            case START -> {

                if (!user.isRegistered()
                        && text.equals(I18n.t("btn_register", lang))) {
                    ctx.setState(user, BotState.WAITING_NAME);
                    send(chatId, I18n.t("enter_name", lang), Keyboards.remove());
                    return;
                }

                if (user.isRegistered()
                        && !user.isLoggedIn()
                        && text.equals(I18n.t("btn_login", lang))) {
                    ctx.setState(user, BotState.LOGIN_LOGIN);
                    send(chatId, I18n.t("enter_login", lang), Keyboards.remove());
                    return;
                }

                if (user.isLoggedIn()
                        && text.equals(I18n.t("btn_profile", lang))) {
                    send(chatId,
                            String.format(
                                    I18n.t("profile", lang),
                                    user.getName(),
                                    user.getPhone(),
                                    user.getEmail(),
                                    user.getStudentClass()
                            ),
                            Keyboards.profile(lang));
                    return;
                }

                if (text.equals(I18n.t("btn_home", lang))) {
                    ctx.setState(user, BotState.START);
                    sendMenu(chatId, user);
                    return;
                }

                if (user.isLoggedIn()
                        && text.equals(I18n.t("btn_schedule", lang))) {

                    String day = WeekDays.today();
                    ctx.setScheduleDay(user, day);

                    send(chatId,
                            scheduleService.buildSchedule(
                                    user.getStudentClass(),
                                    day,
                                    lang
                            ),
                            Keyboards.schedule(lang)
                    );
                    return;
                }

                /* SCHEDULE */

                if (user.isLoggedIn() && (text.equals("➡") || text.equals("⬅"))) {

                    String currentDay = ctx.getScheduleDay(user);
                    String newDay = text.equals("➡")
                            ? WeekDays.next(currentDay)
                            : WeekDays.prev(currentDay);

                    ctx.setScheduleDay(user, newDay);

                    send(chatId,
                            scheduleService.buildSchedule(
                                    user.getStudentClass(),
                                    newDay,
                                    lang
                            ),
                            Keyboards.schedule(lang)
                    );
                    return;
                }


                if (user.isLoggedIn()
                        && text.equals(I18n.t("btn_request", lang))) {
                    ctx.setState(user, BotState.WAITING_REQUEST);
                    send(chatId, I18n.t("request_enter", lang), Keyboards.remove());
                }
            }

            /*  REGISTRATION  */

            case WAITING_NAME -> {
                user.setName(text);
                repo.save(user);
                ctx.setState(user, BotState.WAITING_PHONE);
                send(chatId, I18n.t("enter_phone", lang), null);
            }

            case WAITING_PHONE -> {
                if (!Utils.isValidPhone(text)) {
                    send(chatId, I18n.t("phone_invalid", lang), null);
                    return;
                }
                user.setPhone(text);
                repo.save(user);
                ctx.setState(user, BotState.WAITING_EMAIL);
                send(chatId, I18n.t("enter_email", lang), null);
            }

            case WAITING_EMAIL -> {
                if (!Utils.isValidEmail(text)) {
                    send(chatId, I18n.t("email_invalid", lang), null);
                    return;
                }
                user.setEmail(text);
                repo.save(user);
                ctx.setState(user, BotState.WAITING_CLASS);
                send(chatId, I18n.t("enter_class", lang), Keyboards.classSelect());
            }

            case WAITING_CLASS -> {
                user.setStudentClass(text);
                repo.save(user);
                ctx.setState(user, BotState.WAITING_PASSWORD);
                send(chatId, I18n.t("enter_password", lang), Keyboards.remove());
            }

            case WAITING_PASSWORD -> {
                user.setPassword(passwordEncoder.encode(text));
                repo.save(user);
                ctx.setState(user, BotState.WAITING_PASSWORD_REPEAT);
                send(chatId, I18n.t("repeat_password", lang), null);
            }

            case WAITING_PASSWORD_REPEAT -> {
                if (!passwordEncoder.matches(text, user.getPassword())) {
                    ctx.setState(user, BotState.WAITING_PASSWORD);
                    send(chatId, I18n.t("password_mismatch", lang), null);
                    return;
                }

                user.setPassword(passwordEncoder.encode(text));

                Role roleUser = roleRepo.findByName("ROLE_USER")
                        .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
                user.getRoles().add(roleUser);

                user.setRegistered(true);
                user.setLoggedIn(true);
                repo.save(user);

                ctx.setState(user, BotState.START);
                sendMenu(chatId, user);
            }


            /* LOGIN */

            case LOGIN_LOGIN -> {

                User found = null;

                if (Utils.isValidEmail(text)) {
                    found = repo.findByEmail(text).orElse(null);
                } else if (Utils.isValidPhone(text)) {
                    found = repo.findByPhone(text).orElse(null);
                }

                if (found == null) {
                    send(chatId, I18n.t("login_not_found", lang), null);
                    return;
                }

                found.setChatId(chatId);
                repo.save(found);

                user = found;
                ctx.setState(user, BotState.LOGIN_PASSWORD);
                send(chatId, I18n.t("enter_password", lang), null);
            }

            case LOGIN_PASSWORD -> {
                if (!passwordEncoder.matches(text, user.getPassword())) {
                    send(chatId, I18n.t("password_wrong", lang), null);
                    return;
                }
                user.setLoggedIn(true);
                repo.save(user);
                ctx.setState(user, BotState.START);
                sendMenu(chatId, user);
            }

            /*  EDIT  */

            case EDIT_NAME -> {
                user.setName(text);
                repo.save(user);
                ctx.setState(user, BotState.START);
                sendMenu(chatId, user);
            }

            case EDIT_PHONE -> {
                if (!Utils.isValidPhone(text)) {
                    send(chatId, I18n.t("phone_invalid", lang), null);
                    return;
                }
                user.setPhone(text);
                repo.save(user);
                ctx.setState(user, BotState.START);
                sendMenu(chatId, user);
            }

            case EDIT_EMAIL -> {
                if (!Utils.isValidEmail(text)) {
                    send(chatId, I18n.t("email_invalid", lang), null);
                    return;
                }
                user.setEmail(text);
                repo.save(user);
                ctx.setState(user, BotState.START);
                sendMenu(chatId, user);
            }

            case EDIT_CLASS -> {
                user.setStudentClass(text);
                repo.save(user);
                ctx.setState(user, BotState.START);
                sendMenu(chatId, user);
            }

            case EDIT_PASSWORD_OLD -> {
                if (!text.equals(user.getPassword())) {
                    send(chatId, I18n.t("password_wrong", lang), null);
                    return;
                }
                ctx.setState(user, BotState.EDIT_PASSWORD_NEW);
                send(chatId, I18n.t("enter_new_password", lang), null);
            }

            case EDIT_PASSWORD_NEW -> {
                user.setPassword(text);
                repo.save(user);
                ctx.setState(user, BotState.EDIT_PASSWORD_REPEAT);
                send(chatId, I18n.t("repeat_password", lang), null);
            }

            case EDIT_PASSWORD_REPEAT -> {
                if (!text.equals(user.getPassword())) {
                    ctx.setState(user, BotState.EDIT_PASSWORD_NEW);
                    send(chatId, I18n.t("password_mismatch", lang), null);
                    return;
                }
                ctx.setState(user, BotState.START);
                sendMenu(chatId, user);
            }

            case WAITING_REQUEST -> {
                ctx.setState(user, BotState.START);
                send(chatId, I18n.t("request_done", lang), null);
                sendMenu(chatId, user);
            }
        }
    }

    /* HELPERS */

    private void sendMenu(Long chatId, User user) {
        send(chatId,
                I18n.t("menu", user.getLang()),
                Keyboards.main(
                        user.getLang(),
                        user.isRegistered(),
                        user.isLoggedIn()
                ));
    }

    private void send(Long chatId, String text, ReplyKeyboard keyboard) {
        try {
            SendMessage msg = new SendMessage(chatId.toString(), text);
            if (keyboard != null) msg.setReplyMarkup(keyboard);
            execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}