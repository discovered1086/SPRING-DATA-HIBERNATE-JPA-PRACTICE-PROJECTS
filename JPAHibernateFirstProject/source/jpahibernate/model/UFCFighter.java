package jpahibernate.model;

import javax.persistence.*;
import java.security.PrivateKey;

/**
 * Created by kings on 05-Mar-17.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UFCFighter {
    @Id
    @SequenceGenerator(name = "seqGenerator3", sequenceName = "practice_sequence2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator3")
    private Long Id;
    private String Name;
    private Integer age;

    @Column(name = "resident_of")
    private String hailsFrom;

    public UFCFighter() {
    }

   public UFCFighter(String name, Integer age, String hailsFrom) {
        Name = name;
        this.age = age;
        this.hailsFrom = hailsFrom;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHailsFrom() {
        return hailsFrom;
    }

    public void setHailsFrom(String hailsFrom) {
        this.hailsFrom = hailsFrom;
    }

    public Double getWeight(){
        return null;
    };

}
