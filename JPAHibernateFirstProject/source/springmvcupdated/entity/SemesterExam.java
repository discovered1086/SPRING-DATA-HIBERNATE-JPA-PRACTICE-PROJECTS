package springmvcupdated.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingshuk on 7/4/17.
 */
@Entity
@Table(name="semester_exam",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"semester_year","semester_Type","discipline"})})
public class SemesterExam {

    @Id
    @SequenceGenerator(name = "seqGenerator5", sequenceName = "semester_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator5")
    @Column(name="semester_id")
    private Integer semesterId;

    @Column(name = "semester_year")
    private String semesterYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester_Type")
    private SemesterType semesterType;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    @OneToMany(mappedBy = "exam",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SubjectSemesterMapping> examSubjectMappingList=new ArrayList<SubjectSemesterMapping>();

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }

    public SemesterType getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(SemesterType semesterType) {
        this.semesterType = semesterType;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<SubjectSemesterMapping> getExamSubjectMappingList() {
        return examSubjectMappingList;
    }

    public void setExamSubjectMappingList(List<SubjectSemesterMapping> examSubjectMappingList) {
        this.examSubjectMappingList = examSubjectMappingList;
    }
}
