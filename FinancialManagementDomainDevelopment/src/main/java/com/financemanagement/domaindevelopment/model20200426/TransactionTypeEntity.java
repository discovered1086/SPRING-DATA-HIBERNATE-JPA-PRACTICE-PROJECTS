package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.newenums.TransactionTypeEnum;
import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRANSACTION_TYPE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478300589711095031L;

	@Id
	@Column(name = "TRNSCTN_CD", length = 10)
	@BusinessKey(required = true)
	private String transactionTypeCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRNSCTN_TYP", length = 10)
	@BusinessKey(required = true)
	private TransactionTypeEnum typeOfTransaction;

	@Column(name = "TRNSCTN_TYP_DFNTN", length = 200)
	private String transactionTypeDefinition;

	@Column(name = "TRNSCTN_TYP_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime transactionTypeEffectiveDate;

	@Column(name = "TRNSCTN_TYP_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime transactionTypeTerminationDate;
	

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
