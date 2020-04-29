package com.financemanagement.domaindevelopment.allmodels.model.newmodels.copy;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.newenums.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION_TYPE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3478300589711095031L;

	@Id
	@Column(name="TRNSCTN_CD", length = 10)
	private String transactionTypeCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TRNSCTN_TYP", length = 10)
	private TransactionTypeEnum typeOfTransaction;
	
	@Column(name = "TRNSCTN_TYP_DFNTN", length = 200)
	private String transactionTypeDefinition;
	
	@Column(name = "TRNSCTN_TYP_EFFCTV_DT")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionTypeEffectiveDate;

	@Column(name = "TRNSCTN_TYP_TRMNTN_DT")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionTypeTerminationDate;
}
