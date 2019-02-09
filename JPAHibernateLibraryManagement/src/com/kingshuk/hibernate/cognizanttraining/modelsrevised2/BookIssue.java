package com.kingshuk.hibernate.cognizanttraining.modelsrevised2;

import com.kingshuk.hibernate.cognizanttraining.converters.DateTimeConverter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_issue")
@DynamicInsert
public class BookIssue {

    @Id
    @SequenceGenerator(name="bookIssueGenerator",sequenceName = "BOOK_ISSUE_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookIssueGenerator")
    @Column(name = "book_issue_id",unique = true)
    private Integer bookIssueId;

    @Column(name="issue_date",nullable = false)
    //@Convert(converter = DateTimeConverter.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @Column(name="return_date")
    //@Convert(converter = DateTimeConverter.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name="book_id",referencedColumnName = "book_id")
    private BookInventory book;


    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BookInventory getBook() {
        return book;
    }

    public void setBook(BookInventory book) {
        this.book = book;
    }

    public Integer getBookIssueId() {
        return bookIssueId;
    }

    public void setBookIssueId(Integer bookIssueId) {
        this.bookIssueId = bookIssueId;
    }
}
