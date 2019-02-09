package com.kingshuk.hibernate.cognizanttraining.modelrevised3;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends SystemUser {

    public Customer() {
    }

    public Customer(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name="customer_id",unique = true, nullable = false)
    private Integer customerId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BookIssue> issuedBooks;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Reservation> reservedBooks;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<BookIssue> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<BookIssue> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    public List<Reservation> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(List<Reservation> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }
}
