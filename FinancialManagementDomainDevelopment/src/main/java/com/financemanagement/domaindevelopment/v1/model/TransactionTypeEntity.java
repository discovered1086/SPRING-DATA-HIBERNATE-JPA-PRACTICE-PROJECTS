package com.financemanagement.domaindevelopment.v1.model;

import com.financemanagement.domaindevelopment.v1.model.enums.TransactionTypeEnum;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRANSACTION_TYPE_V1")
@Data
@Builder
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
	
	@Column(name = "TRNSCTN_DFNTN", length = 200)
	private String transactionTypeDefinition;
}
