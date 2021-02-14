package com.financemanagement.domaindevelopment.transactionmodeling.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Embeddable
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class AccountTransactionHelperEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Column(name = "TRNSCTN_ACCT_ID", nullable = false)
	private String transactionAccountId;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	@NotNull(message = "Transaction currency must have a value")
	private CurrencyEntity transactionCurrency;

	@Column(name = "TRNSCTN_AMT", columnDefinition = "NUMBER(20,2)")
	@Positive(message = "Transaction amount must be greater than 0.00")
	private BigDecimal transactionAmount;

	@Column(name = "TRNSCTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	@PastOrPresent(message = "Transaction date must not be a future date")
	private OffsetDateTime transactionDate;

}
