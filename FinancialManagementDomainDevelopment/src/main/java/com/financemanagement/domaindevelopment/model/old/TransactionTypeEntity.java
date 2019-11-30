package com.financemanagement.domaindevelopment.model.old;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.financemanagement.domaindevelopment.enums.old.TransactionTypeEnum;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "TRANSACTION_TYPE")
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
