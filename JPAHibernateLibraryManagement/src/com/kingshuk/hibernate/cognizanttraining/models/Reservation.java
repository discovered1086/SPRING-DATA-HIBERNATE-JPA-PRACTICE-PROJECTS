package com.kingshuk.hibernate.cognizanttraining.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Reservation {

    @EmbeddedId
    private BookCustomerCompositeKey bookCustomerCompositeKey;

    private String reservationDate;

    public BookCustomerCompositeKey getBookCustomerCompositeKey() {
        return bookCustomerCompositeKey;
    }

    public void setBookCustomerCompositeKey(BookCustomerCompositeKey bookCustomerCompositeKey) {
        this.bookCustomerCompositeKey = bookCustomerCompositeKey;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
