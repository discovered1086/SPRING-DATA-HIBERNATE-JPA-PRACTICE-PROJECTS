package bookstore.model.dao;

/**
 * Created by kings on 24-Feb-17.
 */
public class HelloWorld {
    private Integer id;
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
