package academy.prog.repo;

import academy.prog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByChatId(Long chatId);

    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);
}