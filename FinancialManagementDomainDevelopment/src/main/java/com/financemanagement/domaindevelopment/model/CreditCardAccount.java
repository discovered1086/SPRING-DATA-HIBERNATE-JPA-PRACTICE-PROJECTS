package com.financemanagement.domaindevelopment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.financemanagement.domaindevelopment.enums.NetWork;

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
	private NetWork netWork;
	
	@Column(name="credit_balance", columnDefinition= "NUMBER(10,2)")
	private double creditBalance;
	
	@Column(name="credit_card_limit", columnDefinition= "NUMBER(10,2)")
	private double creditCardLimit;

}
