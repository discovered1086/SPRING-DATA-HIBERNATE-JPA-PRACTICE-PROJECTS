package com.financemanagement.domaindevelopment.allmodels.model.old;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.financemanagement.domaindevelopment.allmodels.enums.old.Currency;
import com.financemanagement.domaindevelopment.allmodels.sequencegenerators.CommonSequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER_ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
public class AccountEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540097108451574865L;

	@Id
	@Column(length = 20, name = "ACCT_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSequenceGen")
	@GenericGenerator(name = "accSequenceGen",
	strategy = "com.financemanagement.domaindevelopment.allmodels.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ACCT") })
	private String accountId;

	@Column(length = 50, name = "ACCT_NMBR")
	private String accountNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ACC_CRNCY")
	private Currency currency;

	@Column(length = 100, name = "ACCT_TTLE")
	private String accountDescription;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	private CustomerPortfolioEntity customerPortfolio;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	private List<AccountFeesEntity> accountFeesList;
	
	
}
