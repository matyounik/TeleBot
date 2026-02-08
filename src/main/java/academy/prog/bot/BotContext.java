package academy.prog.bot;

import academy.prog.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BotContext {

    private final Map<Long, BotState> states = new ConcurrentHashMap<>();
    private final Map<Long, String> scheduleDay = new ConcurrentHashMap<>();
    private final Map<Long, Long> selectedStudent = new ConcurrentHashMap<>();

    public BotState getState(User user) {
        return states.getOrDefault(user.getChatId(), BotState.START);
    }

    public void setState(User user, BotState state) {
        states.put(user.getChatId(), state);
    }

    public void clearState(User user) {
        states.remove(user.getChatId());
        scheduleDay.remove(user.getChatId());
        selectedStudent.remove(user.getChatId());
    }

    public String getScheduleDay(User user) {
        return scheduleDay.getOrDefault(user.getChatId(), WeekDays.today());
    }

    public void setScheduleDay(User user, String day) {
        scheduleDay.put(user.getChatId(), day);
    }

    public void setSelectedStudent(User teacher, Long studentId) {
        selectedStudent.put(teacher.getChatId(), studentId);
    }

    public Long getSelectedStudent(User teacher) {
        return selectedStudent.get(teacher.getChatId());
    }
}
