package com.kingshuk.hibernateandjpa.mappingwithmapsidannotation.experiment2;


import com.kingshuk.hibernateandjpa.hibernatesoftdelete.sequencegen.CommonSequenceGenerator;
import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.*;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity(name = "transaction")
@Table(name = "TRANSACTION_MASTER_MAPSID")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class TransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;

	@Version
	private int version;

	@Column(length = 1000, name = "TRNSCTN_DSCRPTN", nullable = false)
	@BusinessKey
	private String transactionDescription;

	@Column(name = "TRNSCTN_ACCT_ID", nullable = false)
	@BusinessKey
	private String transactionAccountId;

	@OneToOne(mappedBy = "parentTransaction", cascade = CascadeType.PERSIST)
	@Fetch(FetchMode.JOIN)
	private TransactionCategoryEntity transactionCategory;

	@Column(name = "TRNSCTN_AMT", columnDefinition = "NUMBER(20,2)")
	@Positive(message = "Transaction amount must be greater than 0.00")
	@BusinessKey(required = true)
	private BigDecimal transactionAmount;

	@Column(name = "TRNSCTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	@PastOrPresent(message = "Transaction date must not be a future date")
	private OffsetDateTime transactionDate;

	@Column(name = "TRNSCTN_NTS", columnDefinition = "VARCHAR2(4000)")
	private String transactionNotes;

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
