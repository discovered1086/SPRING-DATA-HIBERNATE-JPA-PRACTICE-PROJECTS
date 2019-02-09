package com.kingshuk.hibernate.cognizanttraining.modelrevised3;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @SequenceGenerator(name="reservationSequenceGenerator",sequenceName = "RESERVATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationSequenceGenerator")
    @Column(name = "reservation_id",unique = true)
    private Integer reservationId;

    @Column(name = "reservation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    @ManyToOne()
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="book_id",referencedColumnName = "book_id")
    private BookInventory book;


    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
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

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
