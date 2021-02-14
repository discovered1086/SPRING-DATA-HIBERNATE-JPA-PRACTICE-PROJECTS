package com.financemanagement.domaindevelopment.v1.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.v1.model.enums.Currency;
import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "transaction")
@Table(name = "TRANSACTION_MSTR")
@NoArgsConstructor
@Getter
@Setter
public class TransactionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeqGen")
	@GenericGenerator(name = "transactionSeqGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TRN") })
	private String transactionId;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "TRNSCTN_CD", referencedColumnName = "TRNSCTN_CD", nullable = false)
	private TransactionTypeEntity transactionType;

	@Column(length = 1000, name = "TRNSCTN_DSCRPTN")
	private String description;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	private AccountEntity account;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	private CategoryEntity transactionCategory;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TRNSCTN_CRNCY")
	private Currency transactionCurrency;
	
	@Column(name="TRNSCTN_AMT", columnDefinition = "NUMBER(20,2)" )
	private double transactionAmount;
	
	
	/*
	 * I just found out that @Temporal can only be used
	 * with java.util.Date or java.sql.Date
	 */
	@Column(name = "TRNSCTN_DT", columnDefinition="TIMESTAMP")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionDate;
	
	}
