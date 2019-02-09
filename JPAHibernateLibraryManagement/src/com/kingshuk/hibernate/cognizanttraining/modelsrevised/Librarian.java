package com.kingshuk.hibernate.cognizanttraining.modelsrevised;

import javax.persistence.*;

@Entity
@Table(name = "librarian")
public class Librarian extends Customer{
    
    @Column(name = "employee_id",unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
