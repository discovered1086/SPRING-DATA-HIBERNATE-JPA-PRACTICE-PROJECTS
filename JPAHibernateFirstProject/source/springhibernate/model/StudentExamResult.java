package springhibernate.model;

import jpahibernate.model.Student;

import javax.persistence.*;

/**
 * Created by kingshuk on 6/11/17.
 */
@Entity
public class StudentExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer resultId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private EngStudent theStudent;

    private String percentile;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "exam_id")
    private ExamResults examResults;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public EngStudent getTheStudent() {
        return theStudent;
    }

    public void setTheStudent(EngStudent theStudent) {
        this.theStudent = theStudent;
    }

    public String getPercentile() {
        return percentile;
    }

    public void setPercentile(String percentile) {
        this.percentile = percentile;
    }

    public ExamResults getExamResults() {
        return examResults;
    }

    public void setExamResults(ExamResults examResults) {
        this.examResults = examResults;
    }
}
