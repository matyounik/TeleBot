package academy.prog.service;

import academy.prog.model.User;
import academy.prog.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getOrCreate(Long chatId) {
        return repo.findByChatId(chatId)
                .orElseGet(() -> {
                    User u = new User();
                    u.setChatId(chatId);
                    u.setRegistered(false);
                    u.setLang(null);
                    return repo.save(u);
                });
    }

    public void save(User user) {
        repo.save(user);
    }
}