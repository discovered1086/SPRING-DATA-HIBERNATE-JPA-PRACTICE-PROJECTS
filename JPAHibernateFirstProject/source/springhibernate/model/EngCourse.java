package springhibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 26-Feb-17.
 */
@Entity
public class EngCourse {
    @Id
    //@SequenceGenerator(name = "seqGenerator2", sequenceName = "jpa_hibernate.practice_sequence2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_price")
    private Double coursePrice;

    @ManyToMany(mappedBy = "courseList")
    private List<EngStudent> engStudentList =new ArrayList<EngStudent>();

    public EngCourse() {
    }

    public EngCourse(String courseName, Double coursePrice) {
        this.courseName = courseName;
        this.coursePrice = coursePrice;
    }

    public List<EngStudent> getEngStudentList() {
        return engStudentList;
    }

    public void setEngStudentList(List<EngStudent> engStudentList) {
        this.engStudentList = engStudentList;
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
