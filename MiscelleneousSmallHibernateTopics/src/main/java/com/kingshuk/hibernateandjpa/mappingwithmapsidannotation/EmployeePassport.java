package com.kingshuk.hibernateandjpa.mappingwithmapsidannotation;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private String employeeId;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "EMPL_ID")
	@MapsId
	private Employee employee;

	@Column(name = "PSSPRT_NMBR", nullable = false)
	private String passportNumber;

	@Column(name = "PSSPRT_ISS_DT", nullable = false)
	private ZonedDateTime issueDate;

	@Column(name = "PSSPRT_EXPRY_DT")
	private ZonedDateTime expiryDate;

	@Column(name = "term_dt")
	private Date terminationDate;

}
