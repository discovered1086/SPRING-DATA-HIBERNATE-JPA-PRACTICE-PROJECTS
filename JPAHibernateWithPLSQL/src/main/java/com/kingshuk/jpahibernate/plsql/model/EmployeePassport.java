package com.kingshuk.jpahibernate.plsql.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "EMPLOYEE_PASSPORT")
@Data
@Builder
public class EmployeePassport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -528286483060092596L;

	@Id
	@Column(name = "EMPL_ID", nullable = false, unique = true)
	private long employeeId;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "EMPL_ID")
	@MapsId
	private Employee employee;

	@Column(name = "PSSPRT_NMBR", nullable = false)
	private String passportNumber;

	@Column(name = "PSSPRT_ISS_DT", nullable = false, columnDefinition = "TIMESTAMP")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime issueDate;

	@Column(name = "PSSPRT_EXPRY_DT")
	private ZonedDateTime expiryDate;


}
