package com.financemanagement.domaindevelopment.model.old;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_BASED_ACCOUNT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CreditBasedAccountEntity extends AccountEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2748063701793261646L;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "INTRST_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	private List<InterestOnCreditAccountEntity> interestRates;

}
