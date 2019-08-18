package com.financemanagement.domaindevelopment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class LoanAccount extends CreditBasedAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "CRD_ACCT_ID", referencedColumnName = "ACCT_ID")
	private List<CreditBasedAccountStatement> accountStatements;
	
	@Column(name="LN_TRM", length = 10 )
	private Integer loanTerm;

}
