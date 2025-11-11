package academy.prog.service;

import academy.prog.model.User;
import academy.prog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByChatId(long chatId) {
        return userRepository.findByChatId(chatId);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> findUsersPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<User> findByNotifiedFalse() {
        return userRepository.findByNotifiedFalse();
    }

    public List<User> findNewUsers() {
        return userRepository.findByNotifiedFalse();
    }
}