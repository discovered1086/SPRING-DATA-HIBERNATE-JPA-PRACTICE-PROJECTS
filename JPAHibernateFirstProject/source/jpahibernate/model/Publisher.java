package jpahibernate.model;

/**
 * Created by kings on 23-Feb-17.
 */
public class Publisher {
    private String publisher_code;
    private String publisher_name;

    public Publisher() {
    }

    public Publisher(String publisher_code, String publisher_name) {
        this.publisher_code = publisher_code;
        this.publisher_name = publisher_name;
    }

    public String getPublisher_code() {
        return publisher_code;
    }

    public void setPublisher_code(String publisher_code) {
        this.publisher_code = publisher_code;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisher_code='" + publisher_code + '\'' +
                ", publisher_name='" + publisher_name + '\'' +
                '}';
    }
}
