package com.financemanagement.domaindevelopment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.financemanagement.domaindevelopment.enums.BankAccountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bank_account")
public class BankAccount extends Account{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8606614599902139364L;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="account_type")
	private BankAccountType accountType;
	
	@Column(name="account_balance", columnDefinition = "NUMBER(20,2)" )
	private double accountBalance;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "ACCT_STMNT_ID", referencedColumnName = "ACCT_STMNT_ID")
	private List<DebitBasedAccountStatement> accountStatements;

}
