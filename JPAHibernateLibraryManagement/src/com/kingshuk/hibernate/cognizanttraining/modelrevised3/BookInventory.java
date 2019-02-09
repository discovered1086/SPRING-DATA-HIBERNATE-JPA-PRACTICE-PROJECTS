package com.kingshuk.hibernate.cognizanttraining.modelrevised3;

import javax.persistence.*;

@Entity
@Table(name="book_inventory")
public class BookInventory {

    @Id
    @Column(name="book_id")
    @SequenceGenerator(name="bookSequence",sequenceName = "BOOK_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSequence")
    private Integer bookId;

    @Column(name="book_title",nullable = false, unique = true)
    private String bookTitle;

    @Column(name="book_price")
    private Double price;

    @Column(name="book_author", nullable = false)
    private String author;


    @Column(name="book_stock")
    private Integer stock;

    @Version
    @Column(name="version")
    private Integer versionId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }
}
