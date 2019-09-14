package com.kingshuk.hibernateandjpa.mappingwithmapsidannotation;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private long employeeId;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="hire_dt",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Column(name="term_dt")
    @Temporal(TemporalType.DATE)
    private Date terminationDate;

}
