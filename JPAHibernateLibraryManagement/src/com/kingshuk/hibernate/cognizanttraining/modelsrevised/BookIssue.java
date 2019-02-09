package com.kingshuk.hibernate.cognizanttraining.modelsrevised;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "book_issue")
@DynamicInsert
public class BookIssue {

    @EmbeddedId
    private BookCustomerCompositeKey compositeKey;

    @Column(name="issue_date",nullable = false)
    private String issueDate;

    @Column(name="return_date")
    private String returnDate;

    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id",insertable = false, updatable = false)
    private Customer customer;


    @ManyToOne
    @JoinColumn(name="book_id",referencedColumnName = "book_id",insertable = false, updatable = false)
    private BookInventory book;


    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookCustomerCompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(BookCustomerCompositeKey compositeKey) {
        this.compositeKey = compositeKey;
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
}
