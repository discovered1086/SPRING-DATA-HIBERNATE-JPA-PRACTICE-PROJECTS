package com.kingshuk.hibernate.cognizanttraining.modelsrevised2;

import javax.persistence.*;

@Entity
@Table(name = "librarian")
public class Librarian extends Customer {

    @Column(name = "employee_id",unique = true, nullable = false)
    private Integer employeeId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
