package springmvcupdated.entity;

import javax.persistence.*;

/**
 * Created by kingshuk on 7/4/17.
 */
@Entity
@Table(name="student_results",
        uniqueConstraints = {@UniqueConstraint(columnNames = "mapping_id")})
public class StudentExamResults {
    @Id
    @SequenceGenerator(name = "seqGenerator4", sequenceName = "student_result_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator4")
    @Column(name="result_id")
    private Integer resultId;

    //Many to one always means many of this object is tied to
    //Only one object of the class it is mapped to
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name="mapping_id", unique = true)
    private SubjectSemesterMapping subjectSemesterMapping;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name="registration_id")
    private RegistrationInfo student;

    private String grade;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public SubjectSemesterMapping getSubjectSemesterMapping() {
        return subjectSemesterMapping;
    }

    public void setSubjectSemesterMapping(SubjectSemesterMapping subjectSemesterMapping) {
        this.subjectSemesterMapping = subjectSemesterMapping;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public RegistrationInfo getStudent() {
        return student;
    }

    public void setStudent(RegistrationInfo student) {
        this.student = student;
    }
}
