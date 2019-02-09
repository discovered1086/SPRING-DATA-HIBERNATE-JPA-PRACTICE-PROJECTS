package bookstore.model.dao;

import javax.persistence.*;

/**
 * Created by kings on 24-Feb-17.
 */
@Entity
@Table(name = "HELLO_WORLD")
public class HelloWorldAnnotations {

    @Id
    @SequenceGenerator(name = "seqGenerator", sequenceName = "practice_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEXT")
    private String Text;

    public HelloWorldAnnotations() {
    }

    public HelloWorldAnnotations(String text) {
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
