package com.kingshuk.hibernate.cognizanttraining.modelsrevised;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends SystemUser{

    @Column(name="customer_id",unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BookIssue> issuedBooks;

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
}
