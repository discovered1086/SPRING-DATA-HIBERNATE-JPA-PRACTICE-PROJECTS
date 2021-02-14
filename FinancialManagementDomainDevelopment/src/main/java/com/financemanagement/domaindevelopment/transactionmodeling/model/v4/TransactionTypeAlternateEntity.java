package com.financemanagement.domaindevelopment.transactionmodeling.model.v4;


import com.financemanagement.domaindevelopment.v2.model.enums.TransactionTypeEnum;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "TRANSACTION_TYPE_V4")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeAlternateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478300589711095031L;

	@Id
	@Column(name = "TRNSCTN_TYP_CD", length = 30)
	private String transactionMethodCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRNSCTN_TYPE", length = 30)
	private TransactionTypeEnum typeOfTransaction;

	@Column(name = "TRNSCTN_TYPE_DFNTN", length = 200)
	private String transactionMethodDefinition;

	@Column(name = "TRNSCTN_TYPE_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	//@DateNotInPast(message = "transactionTypeEffectiveDate should NOT be in the past")
	private OffsetDateTime transactionMethodEffectiveDate;

	@Column(name = "TRNSCTN_TYPE_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime transactionMethodTerminationDate;

}
