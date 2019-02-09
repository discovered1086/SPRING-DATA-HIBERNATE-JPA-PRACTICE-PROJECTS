package jpahibernate.model;

/**
 * Created by kings on 23-Feb-17.
 */
public class Chapter {
    private String book_isbn;
    private Integer chapter_num;
    private String title;

    public Chapter() {
    }

    public Chapter(Integer chapter_num, String title) {
        this.chapter_num = chapter_num;
        this.title = title;
    }

    public String getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }

    public Integer getChapter_num() {
        return chapter_num;
    }

    public void setChapter_num(Integer chapter_num) {
        this.chapter_num = chapter_num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "book_isbn='" + book_isbn + '\'' +
                ", chapter_num=" + chapter_num +
                ", title='" + title + '\'' +
                '}';
    }
}
