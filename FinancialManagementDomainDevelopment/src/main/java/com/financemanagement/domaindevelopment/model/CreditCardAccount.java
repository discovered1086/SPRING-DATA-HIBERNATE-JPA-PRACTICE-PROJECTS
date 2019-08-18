package com.financemanagement.domaindevelopment.model;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.CreditCardNetWork;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="credit_card_account")
public class CreditCardAccount extends Account{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8036997190322227104L;

	@Column(name="credit_card_number", length=20)
	private String creditCardNumber;
		
	@Enumerated(EnumType.STRING)
	private CreditCardNetWork netWork;
	
	@Column(name="credit_balance", columnDefinition= "NUMBER(10,2)")
	private double creditBalance;
	
	@Column(name="credit_card_limit", columnDefinition= "NUMBER(10,2)")
	private double creditCardLimit;
	
	@Column(name = "CRD_EXPRY_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime cardExpiryDate;
	
	@Column(name = "CRD_CVV", length = 5)
	private Integer cvvNumber;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "ACCT_STMNT_ID", referencedColumnName = "ACCT_STMNT_ID")
	private List<CreditCardAccountStatement> accountStatements;

}
