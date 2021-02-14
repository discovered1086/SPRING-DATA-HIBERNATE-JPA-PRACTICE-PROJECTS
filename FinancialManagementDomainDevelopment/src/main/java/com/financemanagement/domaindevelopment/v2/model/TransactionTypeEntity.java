package com.financemanagement.domaindevelopment.v2.model;

import com.financemanagement.domaindevelopment.v2.model.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TRANSACTION_TYPE_V2")
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
