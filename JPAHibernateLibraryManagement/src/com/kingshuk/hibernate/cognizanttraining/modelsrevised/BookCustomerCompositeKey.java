package com.kingshuk.hibernate.cognizanttraining.modelsrevised;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BookCustomerCompositeKey implements Serializable {

    @Column(name="customer_id")
    private String customerId;


    @Column(name="book_id")
    private Integer bookId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
