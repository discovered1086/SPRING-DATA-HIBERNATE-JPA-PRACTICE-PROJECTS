package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.Type;

import com.openpojo.business.annotation.BusinessKey;

@Embeddable
public class AccountTransactionDetailsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8810036515156583885L;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	@NotNull(message = "Transaction must be associated to an account")
	@BusinessKey(required = true)
	private AccountEntity account;
	
	@ManyToOne
	@JoinColumn(name = "TRNSCTN_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	@NotNull(message = "Transaction currency must have a value")
	@BusinessKey(required = true)
	private CurrencyEntity transactionCurrency;

	@Column(name = "TRNSCTN_AMT", columnDefinition = "NUMBER(20,2)")
	@Positive(message = "Transaction amount must be greater than 0.00")
	@BusinessKey(required = true)
	private BigDecimal transactionAmount;

	@Column(name = "TRNSCTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	@PastOrPresent(message = "Transaction date must not be a future date")
	private OffsetDateTime transactionDate;
}
