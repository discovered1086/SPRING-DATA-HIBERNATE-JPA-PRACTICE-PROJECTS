package jpahibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by kings on 26-Feb-17.
 */
@Entity
public class Student {
    @Id
    @SequenceGenerator(name = "seqGenerator7", sequenceName = "practice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator7")
    private Integer Id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "year")
    private Integer batchYear;
    private String major;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "student_course",
                         joinColumns = {@JoinColumn(name = "student_id")},
                         inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courseList;

    public Student() {
    }

    public Student(String studentName, Integer batchYear, String major) {
        this.studentName = studentName;
        this.batchYear = batchYear;
        this.major = major;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
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
}
