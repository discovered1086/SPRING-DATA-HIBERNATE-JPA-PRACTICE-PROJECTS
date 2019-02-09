package springhibernate.model;

import javax.persistence.*;

/**
 * Created by kingshuk on 6/11/17.
 */
@Entity
public class Discipline {
    @Id
    @Column(name = "discipline_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer disciplineId;

    @Column(name = "discipline_name")
    private String disciplineName;

    @OneToOne(mappedBy = "discipline",cascade = CascadeType.PERSIST)
    private ExamResults examResults;


    @OneToOne(mappedBy = "discipline")
    private EngStudent student;

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public ExamResults getExamResults() {
        return examResults;
    }

    public void setExamResults(ExamResults examResults) {
        this.examResults = examResults;
    }

    public EngStudent getStudent() {
        return student;
    }

    public void setStudent(EngStudent student) {
        this.student = student;
    }
}
