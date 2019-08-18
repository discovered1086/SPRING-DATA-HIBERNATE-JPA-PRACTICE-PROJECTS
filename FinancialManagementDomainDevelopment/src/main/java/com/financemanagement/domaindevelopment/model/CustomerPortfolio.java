package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

@Entity
@Table(name = "CUSTOMER_PORTFOLIO")
public class CustomerPortfolio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 388460267701165201L;
	
	@Id
	@Column(length = 20, name = "PRTFLIO_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerPortfolioSeqGen")
	@GenericGenerator(name = "customerPortfolioSeqGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "PRTFLIO")})
	private String portFolioId;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "CSTMR_ID", referencedColumnName = "CSTMR_ID")
	private Customer customerId;
	
	@OneToMany(cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "PRTFLIO_ACCT_MAPPING", joinColumns = {
			@JoinColumn(name = "PRTFLIO_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "ACCT_ID") })
	private List<Account> customerAccounts;
	
	@OneToMany(cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "PRTFLIO_TRN_MAPPING", joinColumns = {
			@JoinColumn(name = "PRTFLIO_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "TRNSCTN_ID") })
	private List<Transaction> unAccountedTransactions;

}
