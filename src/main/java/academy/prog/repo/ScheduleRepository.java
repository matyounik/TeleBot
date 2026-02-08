package academy.prog.repo;

import academy.prog.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository
        extends JpaRepository<Schedule, Long> {

    List<Schedule> findBySchoolClassAndDay(
            String schoolClass,
            String day
    );
}