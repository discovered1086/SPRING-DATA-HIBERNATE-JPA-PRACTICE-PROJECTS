package springmvcupdated.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kingshuk on 7/4/17.
 */
@Entity
/*@Table(name = "student",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email_address")})*/
@Table(name="student")
public class Student extends RegistrationInfo{
    /*@Id
    @SequenceGenerator(name = "seqGenerator7", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator7")
    @GenericGenerator(name="seqGenerator7", strategy = "springmvcupdated.util.StudentSequenceGenerator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator7")
    @Column(name = "student_id")
    private Integer Id;*/

    //@NotNull(message = "Admission Year can't be empty")
    @Column(name = "admisssion_year")
    private Integer batchYear;

    //@NotNull(message = "Roll No can't be empty")
    @Column(name="roll_no")
    private Integer rollNo;
    /*@Column(name = "email_address", unique = true) //OR we could do something like this for declaring any field as unique
    @Column(name = "email_address", nullable = false)
    private String emailAddress;*/

    //@DisciplineValidation
    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    @ManyToMany(mappedBy = "studentsEnrolledList", cascade = {CascadeType.ALL})
    private List<SubjectSemesterMapping> studentExamMapping=new LinkedList<SubjectSemesterMapping>();

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<StudentExamResults> studentExamResults = new ArrayList<StudentExamResults>();

    /*public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }*/

    public Integer getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(Integer batchYear) {
        this.batchYear = batchYear;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<StudentExamResults> getStudentExamResults() {
        return studentExamResults;
    }

    public void setStudentExamResults(List<StudentExamResults> studentExamResults) {
        this.studentExamResults = studentExamResults;
    }

    /*public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }*/

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }


    public List<SubjectSemesterMapping> getStudentExamMapping() {
        return studentExamMapping;
    }

    public void setStudentExamMapping(List<SubjectSemesterMapping> studentExamMapping) {
        this.studentExamMapping = studentExamMapping;
    }
}
