package springmvcupdated.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingshuk on 7/7/17.
 */
@Entity
@Table(name="subject_semester",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"semester_id","subject_id"})})
public class SubjectSemesterMapping {
    @Id
    @SequenceGenerator(name = "seqGenerator3", sequenceName = "student_sem_mapping_seq", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator3")
    @Column(name="mapping_id")
    private Integer id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "student_semester_subject",
            joinColumns = {@JoinColumn(name = "mapping_id")},
            inverseJoinColumns = {@JoinColumn(name = "registration_id")})
    private List<Student> studentsEnrolledList=new ArrayList<Student>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name="semester_id")
    private SemesterExam exam;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    private DisciplineSubjects disciplineSubjects;

    @OneToOne(mappedBy = "subjectSemesterMapping",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private StudentExamResults examResults;

    public SubjectSemesterMapping() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Student> getStudentsEnrolledList() {
        return studentsEnrolledList;
    }

    public void setStudentsEnrolledList(List<Student> studentsEnrolledList) {
        this.studentsEnrolledList = studentsEnrolledList;
    }

    public SemesterExam getExam() {
        return exam;
    }

    public void setExam(SemesterExam exam) {
        this.exam = exam;
    }

    public DisciplineSubjects getDisciplineSubjects() {
        return disciplineSubjects;
    }

    public void setDisciplineSubjects(DisciplineSubjects disciplineSubjects) {
        this.disciplineSubjects = disciplineSubjects;
    }

    public StudentExamResults getExamResults() {
        return examResults;
    }

    public void setExamResults(StudentExamResults examResults) {
        this.examResults = examResults;
    }
}
