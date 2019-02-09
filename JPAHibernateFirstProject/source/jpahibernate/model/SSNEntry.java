package jpahibernate.model;

import javax.persistence.*;

/**
 * Created by kings on 26-Feb-17.
 */
@Entity
public class SSNEntry {
    @Id
    @SequenceGenerator(name = "seqGenerator6", sequenceName = "practice_sequence2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator6")
    private Integer Id;

    @Column(name = "ssn_number")
    private Integer ssnNumber;

    @OneToOne(mappedBy = "ssn",cascade = CascadeType.PERSIST)
    private Citizen citizen;

    public SSNEntry() {
    }

    public SSNEntry(Integer ssnNumber) {
        this.ssnNumber = ssnNumber;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getSsnNumber() {
        return ssnNumber;
    }

    public void setSsnNumber(Integer ssnNumber) {
        this.ssnNumber = ssnNumber;
    }
}
