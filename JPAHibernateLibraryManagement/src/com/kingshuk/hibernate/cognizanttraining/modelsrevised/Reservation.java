package com.kingshuk.hibernate.cognizanttraining.modelsrevised;

import javax.persistence.*;

@Entity
@Table(name="reservation")
public class Reservation {

    @EmbeddedId
    private BookCustomerCompositeKey bookCustomerCompositeKey;

    @Column(name = "reservation_date", nullable = false)
    private String reservationDate;

    @ManyToOne()
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id",insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="book_id",referencedColumnName = "book_id",insertable = false, updatable = false)
    private BookInventory book;

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
