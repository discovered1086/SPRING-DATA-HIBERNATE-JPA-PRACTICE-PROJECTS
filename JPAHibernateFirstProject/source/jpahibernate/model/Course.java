package jpahibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 26-Feb-17.
 */
@Entity
public class Course {
    @javax.persistence.Id
    @SequenceGenerator(name = "seqGenerator2", sequenceName = "practice_sequence2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator2")
    private Integer Id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_price")
    private Double coursePrice;

    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList=new ArrayList<Student>();

    public Course() {
    }

    public Course(String courseName, Double coursePrice) {
        this.courseName = courseName;
        this.coursePrice = coursePrice;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }
}
