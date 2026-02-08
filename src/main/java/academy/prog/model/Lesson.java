package academy.prog.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Getter @Setter
public class Lesson {

    @Id @GeneratedValue
    private Long id;

    private int schoolClass;
    private DayOfWeek day;
    private String subject;
    private String room;
    private LocalTime time;
}