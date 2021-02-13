package com.kingshuk.hibernateandjpa.mappingwithmapsidannotation.experiment1;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE_MAPSID")
@DynamicInsert
@Data
@Builder
public class Employee {

    @Id
    @Column(name="employee_id",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employeeId;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="hire_dt",nullable = false)
    private Date hireDate;

    @Column(name="term_dt")
    private Date terminationDate;

}
