package jpahibernate.model;

import javax.persistence.*;

/**
 * Created by kings on 26-Feb-17.
 */
@Entity
public class Citizen {
    @Id
    @SequenceGenerator(name = "seqGenerator1", sequenceName = "practice_sequence2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator1")
    private Integer Id;

    @Column(name = "citizen_name")
    private String citizenName;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ssn_id", unique = true)
    private SSNEntry ssn;

    public Citizen() {
    }

    public Citizen(String citizenName, SSNEntry ssn) {
        this.citizenName = citizenName;
        this.ssn = ssn;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public SSNEntry getSsn() {
        return ssn;
    }

    public void setSsn(SSNEntry ssn) {
        this.ssn = ssn;
    }
}
