package academy.prog.bot;

import academy.prog.model.Schedule;
import academy.prog.repo.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repo;

    public String buildSchedule(String schoolClass, String day, String lang) {

        List<Schedule> list =
                repo.findBySchoolClassAndDay(schoolClass, day);

        if (list.isEmpty()) {
            return I18n.t("no_lessons", lang);
        }

        StringBuilder sb = new StringBuilder("📅 " + day + "\n\n");

        for (Schedule s : list) {
            sb.append("• ")
                    .append(s.getSubject())
                    .append(" (")
                    .append(s.getRoom())
                    .append(") — ")
                    .append(s.getTime())
                    .append("\n");
        }

        return sb.toString();
    }
}