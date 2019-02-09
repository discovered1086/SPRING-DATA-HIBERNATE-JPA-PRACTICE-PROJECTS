package jpahibernate.model;

import javax.persistence.*;

/**
 * Created by kings on 24-Feb-17.
 */
@Entity
@Table(name = "HELLO_WORLD")
public class HelloWorld {

    @Id
    @SequenceGenerator(name = "seqGenerator4", sequenceName = "practice_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator4")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEXT")
    private String Text;

    public HelloWorld() {
    }

    public HelloWorld(String text) {
        Text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
