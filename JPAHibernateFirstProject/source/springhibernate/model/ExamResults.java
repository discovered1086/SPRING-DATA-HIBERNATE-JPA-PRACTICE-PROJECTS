package springhibernate.model;

import javax.persistence.*;

/**
 * Created by kingshuk on 6/11/17.
 */
@Entity
@Table(name = "exam_results")
public class ExamResults {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "exam_year")
    private String examYear;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "discipline_id", unique = true)
   private Discipline discipline;

    @OneToOne(mappedBy = "examResults",cascade = CascadeType.PERSIST)
    private StudentExamResult studentExamResult;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamYear() {
        return examYear;
    }

    public void setExamYear(String examYear) {
        this.examYear = examYear;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public StudentExamResult getStudentExamResult() {
        return studentExamResult;
    }

    public void setStudentExamResult(StudentExamResult studentExamResult) {
        this.studentExamResult = studentExamResult;
    }
}
