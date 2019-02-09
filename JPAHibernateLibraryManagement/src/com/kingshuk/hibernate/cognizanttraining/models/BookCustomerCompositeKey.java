package com.kingshuk.hibernate.cognizanttraining.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookCustomerCompositeKey implements Serializable {
    private Integer customerId;
    private Integer bookId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
