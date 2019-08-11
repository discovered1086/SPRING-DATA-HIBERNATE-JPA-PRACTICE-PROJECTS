package com.financemanagement.domaindevelopment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="LOAN_ACCOUNT")
@Getter
@Setter
public class LoanAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "ACCT_STMNT_ID", referencedColumnName = "ACCT_STMNT_ID")
	private List<CreditBasedAccountStatement> accountStatements;

}
