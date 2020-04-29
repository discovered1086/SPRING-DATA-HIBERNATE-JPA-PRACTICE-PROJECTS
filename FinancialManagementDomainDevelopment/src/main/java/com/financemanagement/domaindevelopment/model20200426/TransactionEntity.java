package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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

@Entity(name = "transaction")
@Table(name = "TRANSACTION_MASTER")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@DynamicInsert(true)
@DynamicUpdate(true)
public class TransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeqGen")
	@GenericGenerator(name = "transactionSeqGen", strategy = "com.financemanagement.transaction.persistence.entities.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TRN") })
	private String transactionId;

	@Version
	private int version;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "TRNSCTN_CD", referencedColumnName = "TRNSCTN_CD", nullable = false)
	@NotNull(message = "Transaction type must not be null")
	@BusinessKey(required = true)
	private TransactionTypeEntity transactionType;

	@Column(length = 1000, name = "TRNSCTN_DSCRPTN", nullable = false)
	@NotBlank(message = "Transaction description can't be empty")
	@BusinessKey(required = true)
	private String transactionDescription;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	@NotNull(message = "Transaction must have an associated category")
	@BusinessKey(required = true)
	private CategoryEntity transactionCategory;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(columnDefinition = "VARCHAR2(20)", name = "TRNSCTN_ID", referencedColumnName = "TRNSCTN_ID")
	@MapsId
	@BusinessKey(required = false)
	private TransactionLocationEntity transactionLocation;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(columnDefinition = "VARCHAR2(20)", name = "TRNSCTN_ID", referencedColumnName = "TRNSCTN_ID")
	@MapsId
	@BusinessKey(required = false)
	private AccountTransactionAbstractEntity accountTransaction;

	@Column(name = "TRNSCTN_NTS", columnDefinition = "VARCHAR2(4000)")
	@Size(min = 0, max = 4000)
	private String transactionNotes;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.REMOVE }, fetch = FetchType.EAGER, mappedBy = "parentTransaction")
	private Set<TransactionItemEntity> itemizedTransactions;

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
