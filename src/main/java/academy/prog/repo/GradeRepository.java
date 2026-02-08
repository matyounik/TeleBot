package academy.prog.repo;

import academy.prog.model.Grade;
import academy.prog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(User student);
}