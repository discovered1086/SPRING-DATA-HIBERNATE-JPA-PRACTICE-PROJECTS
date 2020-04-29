package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.financemanagement.domaindevelopment.allmodels.sequencegenerators.CommonSequenceGenerator;
import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "transactionItem")
@Table(name = "TRANSACTION_ITEM")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class TransactionItemEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ITM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemizedTransactionSeqGen")
	@GenericGenerator(name = "itemizedTransactionSeqGen", strategy = "com.financemanagement.transaction.persistence.entities.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ITMTRN") })
	private String transactionItemId;

	@Version
	private int version;

	@Column(length = 1000, name = "TRNSCTN_ITM_DSCRPTN", nullable = false)
	@NotBlank(message = "Itemized transaction description can't be empty")
	@BusinessKey(required = true)
	private String transactionItemDescription;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_ITM_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	@NotNull(message = "Transaction currency must have a value")
	@BusinessKey(required = true)
	private CurrencyEntity transactionItemCurrency;

	@Column(name = "TRNSCTN_ITM_AMT", columnDefinition = "NUMBER(20,2)")
	@Positive(message = "Transaction amount must be greater than 0.00")
	@BusinessKey(required = true)
	private BigDecimal transactionItemAmount;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PRNT_TRNSCTN_ID", referencedColumnName = "TRNSCTN_ID", nullable = false, updatable = false)
	@BusinessKey(required = true)
	private TransactionEntity parentTransaction;

	@Override
	public int hashCode() {
		return BusinessIdentity.getHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return BusinessIdentity.areEqual(this, obj);
	}

	@Override
	public String toString() {
		return BusinessIdentity.toString(this);
	}

}
