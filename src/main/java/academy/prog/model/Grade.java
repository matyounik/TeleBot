package academy.prog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @Column(name = "grade_value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    /*  GETTERS / SETTERS */

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getValue() {
        return value;
    }

    public User getStudent() {
        return student;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}