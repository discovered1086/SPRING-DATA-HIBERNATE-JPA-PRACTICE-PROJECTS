package bookstore.model;

import java.util.List;

/**
 * Created by kings on 23-Feb-17.
 */
public class Book {
    private String isbn;
    private String bookName;
    private Publisher publisher;
    private List<Chapter> chaptersList;

    public Book() {
    }

    public Book(String isbn, String bookName, Publisher publisher) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Chapter> getChaptersList() {
        return chaptersList;
    }

    public void setChaptersList(List<Chapter> chaptersList) {
        this.chaptersList = chaptersList;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", publisher=" + publisher +
                ", chaptersList=" + chaptersList +
                '}';
    }
}
