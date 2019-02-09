package com.kingshuk.hibernate.cognizanttraining.modelrevised3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "librarian")
public class Librarian extends Customer {

    public Librarian() {
    }

    public Librarian(Integer customerId, Integer employeeId) {
        super(customerId);
        this.employeeId = employeeId;
    }

    @Column(name = "employee_id",unique = true, nullable = false)
    private Integer employeeId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
