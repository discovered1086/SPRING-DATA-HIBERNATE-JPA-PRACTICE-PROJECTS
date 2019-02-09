package springhibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 26-Feb-17.
 */
@Entity
public class EngStudent {
    @Id
    //@SequenceGenerator(name = "seqGenerator7", sequenceName = "jpa_hibernate.practice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "admisssion_year")
    private Integer batchYear;

    private String major;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "eng_student_course",
                         joinColumns = {@JoinColumn(name = "student_id")},
                         inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<EngCourse> courseList=new ArrayList<EngCourse>();

    @OneToMany(mappedBy = "theStudent",cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch=FetchType.LAZY)
    private List<StudentExamResult> examResultsList=new ArrayList<StudentExamResult>();


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.LAZY)
    private Discipline discipline;

    public EngStudent() {
    }

    public EngStudent(String studentName, Integer batchYear, String major) {
        this.studentName = studentName;
        this.batchYear = batchYear;
        this.major = major;
    }

    public List<EngCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<EngCourse> courseList) {
        this.courseList = courseList;
    }

    public List<StudentExamResult> getExamResultsList() {
        return examResultsList;
    }

    public void setExamResultsList(List<StudentExamResult> examResultsList) {
        this.examResultsList = examResultsList;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(Integer batchYear) {
        this.batchYear = batchYear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "EngStudent{" +
                "Id=" + Id +
                ", studentName='" + studentName + '\'' +
                ", batchYear=" + batchYear +
                ", major='" + major + '\'' +
                ", courseList=" + courseList +
                ", examResultsList=" + examResultsList +
                ", discipline=" + discipline +
                '}';
    }
}
