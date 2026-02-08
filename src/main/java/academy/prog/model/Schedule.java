package academy.prog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String room;

    @Column(name = "week_day")
    private String day;

    @Column(name = "lesson_time")
    private String time;

    @Column(name = "school_class")
    private String schoolClass;


    /*  GETTERS / SETTERS */

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getRoom() {
        return room;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getSchoolClass() {
        return schoolClass;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }
}