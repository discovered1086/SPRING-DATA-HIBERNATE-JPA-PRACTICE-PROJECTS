package com.financemanagement.domaindevelopment.v2.model;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity(name = "transaction")
@Table(name = "TRANSACTION_MASTER_V2")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class TransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeqGen")
	@GenericGenerator(name = "transactionSeqGen", strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TRN") })
	private String transactionId;

	@Version
	private int version;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "TRNSCTN_CD", referencedColumnName = "TRNSCTN_CD", nullable = false)
	@NotNull(message = "Transaction type must not be null")
	private TransactionTypeEntity transactionType;

	@Column(length = 1000, name = "TRNSCTN_DSCRPTN", nullable = false)
	@NotBlank(message = "Transaction description can't be empty")
	private String transactionDescription;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	@NotNull(message = "Transaction must be associated to an account")
	private AccountEntity account;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	@NotNull(message = "Transaction must have an associated category")
	private CategoryEntity transactionCategory;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	@NotNull(message = "Transaction currency must have a value")
	private CurrencyEntity transactionCurrency;

	@Column(name = "TRNSCTN_AMT", columnDefinition = "NUMBER(20,2)")
	@Positive(message = "Transaction amount must be greater than 0.00")
	private BigDecimal transactionAmount;

	/*
	 * I just found out that @Temporal can only be used with java.util.Date or
	 * java.sql.Date
	 */
	@Column(name = "TRNSCTN_DT", columnDefinition = "TIMESTAMP")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	@PastOrPresent(message = "Transaction date must not be a future date")
	private ZonedDateTime transactionDate;
	
	@Column(name = "TRNSCTN_NTS", columnDefinition = "VARCHAR2(4000)")
	@Size(min = 0, max = 4000)
	private String transactionNotes;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transactionId).append(transactionDate).append(transactionCurrency)
				.append(transactionCategory).append(transactionAmount).append(transactionType)
				.append(transactionDescription).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TransactionEntity) {
			final TransactionEntity transactionEntity = (TransactionEntity) obj;


			boolean transactionEquality = new EqualsBuilder()
					.append(this.transactionDate, transactionEntity.transactionDate)
					.append(this.transactionCurrency, transactionEntity.transactionCurrency)
					.append(this.transactionAmount, transactionEntity.transactionAmount)
					.append(this.transactionCategory, transactionEntity.transactionCategory)
					.append(this.account, transactionEntity.account)
					.append(this.transactionDescription.trim().toUpperCase(),
							transactionEntity.transactionDescription.trim().toUpperCase())
					.append(this.transactionType, transactionEntity.transactionType).isEquals();

			return (((Objects.nonNull(this.transactionId) && Objects.nonNull(transactionEntity.transactionId))
					&& this.transactionId.equals(transactionEntity.transactionId)) || transactionEquality);
		} else {
			return false;
		}
	}

}
