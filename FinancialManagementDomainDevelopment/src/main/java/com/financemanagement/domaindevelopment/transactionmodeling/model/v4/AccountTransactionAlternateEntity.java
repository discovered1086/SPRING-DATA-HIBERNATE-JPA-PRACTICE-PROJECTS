package com.financemanagement.domaindevelopment.transactionmodeling.model.v4;

import com.financemanagement.domaindevelopment.sequencegenerators.newsequences.CommonSequenceGenerator;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CurrencyEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ACCOUNT_TRANSACTION_DETAILS_V4")
@NoArgsConstructor
@Builder
@Data
@ToString(exclude = "transactionMasterEntity")
@EqualsAndHashCode(exclude = "transactionMasterEntity")
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class AccountTransactionAlternateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "ACCT_TRN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeqGen")
	@GenericGenerator(name = "transactionSeqGen", strategy = "com.financemanagement.domaindevelopment.sequencegenerators.newsequences.CommonSequenceGenerator"
			, parameters = {
			@org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ACCT_TRN") })
	private String transactionId;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "TRNSCTN_ID", referencedColumnName = "TRNSCTN_ID",nullable = false)
	private TransactionMasterAlternateEntity transactionMasterEntity;

	@Column(name = "TRNSCTN_ACCT_ID", nullable = false)
	private String transactionAccountId;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_TYP_CD", referencedColumnName = "TRNSCTN_TYP_CD", nullable = false)
	private TransactionTypeAlternateEntity transactionMethod;

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
