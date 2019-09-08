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
@Table(name="BANK_ACCOUNT")
public class BankAccountEntity extends AccountEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8606614599902139364L;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="ACCT_TYP")
	private BankAccountType accountType;
	
	@Column(name="AVLBLE_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double availableBalance;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "CUST_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	private List<DebitBasedAccountStatementEntity> accountStatements;

}
