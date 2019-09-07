package com.financemanagement.domaindevelopment.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CREDIT_ACCOUNT_STATEMENT_SUMMARY")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class CreditBasedAccountStatementEntity extends AccountStatementSummaryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556955442948517767L;
	
	@Column(name="PYMNT_DUE", columnDefinition = "NUMBER(20,2)" )
	private double paymentDue;
	
	@Column(name="PYMNT_DUE_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime paymentDueDate;

}
