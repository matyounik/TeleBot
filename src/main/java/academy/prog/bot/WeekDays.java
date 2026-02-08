package academy.prog.bot;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekDays {

    private static final List<String> DAYS =
            List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY");

    public static String today() {
        DayOfWeek d = LocalDate.now().getDayOfWeek();
        return DAYS.contains(d.name()) ? d.name() : "MONDAY";
    }

    public static String next(String day) {
        int i = DAYS.indexOf(day);
        return DAYS.get((i + 1) % DAYS.size());
    }

    public static String prev(String day) {
        int i = DAYS.indexOf(day);
        return DAYS.get((i - 1 + DAYS.size()) % DAYS.size());
    }
}