package academy.prog.repo;

import academy.prog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByChatId(long chatId);

    Page<User> findAll(Pageable pageable);

    List<User> findByNotifiedFalse();
}