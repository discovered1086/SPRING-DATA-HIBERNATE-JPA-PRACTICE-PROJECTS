package springmvcupdated.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingshuk on 7/4/17.
 */
@Entity
@Table(name="subjects")
public class DisciplineSubjects {
    @Id
    @SequenceGenerator(name = "seqGenerator6", sequenceName = "subject_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator6")
    @Column(name="subject_id")
    private Integer id;

    @Column(name="subject")
    private String subject;

    @Column(name="subject_price")
    private Double subjectPrice;

    @Column(name="subject_desc")
    private String subjectDescription;

    @OneToMany(mappedBy = "disciplineSubjects",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SubjectSemesterMapping> examSubjectMappingList=new ArrayList<SubjectSemesterMapping>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getSubjectPrice() {
        return subjectPrice;
    }

    public void setSubjectPrice(Double subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public List<SubjectSemesterMapping> getExamSubjectMappingList() {
        return examSubjectMappingList;
    }

    public void setExamSubjectMappingList(List<SubjectSemesterMapping> examSubjectMappingList) {
        this.examSubjectMappingList = examSubjectMappingList;
    }
}
