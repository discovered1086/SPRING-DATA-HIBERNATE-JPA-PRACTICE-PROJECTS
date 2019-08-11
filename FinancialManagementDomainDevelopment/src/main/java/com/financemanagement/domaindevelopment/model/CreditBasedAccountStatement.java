package com.financemanagement.domaindevelopment.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CREDIT_ACCT_STMNT")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CreditBasedAccountStatement extends AccountStatement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556955442948517767L;
	
	@Column(name="STMNT_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double statementBalance;
	
	@Column(name="PYMNT_DUE", columnDefinition = "NUMBER(20,2)" )
	private double paymentDue;
	
	@Column(name="PYMNT_DUE_DT")
	@Temporal(TemporalType.DATE)
	private ZonedDateTime paymentDueDate;

}
