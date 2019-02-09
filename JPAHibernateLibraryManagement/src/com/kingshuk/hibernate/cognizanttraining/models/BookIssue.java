package com.kingshuk.hibernate.cognizanttraining.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book_issue")
public class BookIssue {

    @EmbeddedId
    private BookCustomerCompositeKey compositeKey;

    private String issueDate;

    private String returnDate;


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
}
