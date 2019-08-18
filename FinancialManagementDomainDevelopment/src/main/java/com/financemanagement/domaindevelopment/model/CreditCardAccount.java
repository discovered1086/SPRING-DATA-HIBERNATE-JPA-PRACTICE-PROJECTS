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
@Table(name="CREDIT_CARD_ACCOUNT")
public class CreditCardAccount extends CreditBasedAccount{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8036997190322227104L;

	@Column(name="CRDT_CRD_NMBR", length=20)
	private String creditCardNumber;
		
	@Enumerated(EnumType.STRING)
	private CreditCardNetWork netWork;
	
	@Column(name="CRDT_CRD_BLNC", columnDefinition= "NUMBER(10,2)")
	private double creditBalance;
	
	@Column(name="CRDT_CRD_LMT", columnDefinition= "NUMBER(10,2)")
	private double creditCardLimit;
	
	@Column(name = "CRDT_CRD_EXPRY_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime cardExpiryDate;
	
	@Column(name = "CRDT_CRD_CVV", length = 5)
	private Integer cvvNumber;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "CRD_ACCT_ID", referencedColumnName = "ACCT_ID", nullable= false)
	private List<CreditCardAccountStatement> accountStatements;

}
