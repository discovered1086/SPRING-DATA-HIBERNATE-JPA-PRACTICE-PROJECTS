package com.hibernatepractice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGen")
	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "TRANSACTION_ID")
	private long transactionId;
	
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private FinancialAccountEntity account;

	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;
	
	@Column(name = "TRANSACTION_AMOUNT", columnDefinition = "NUMBER(20,2)")
	private BigDecimal transactionAmount;

	@Column(name = "LAST_UPDATED_DATE")
	private LocalDateTime lastUpdatedDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;

	@Column(name = "TRANSACTION_NOTES")
	private String transactionNotes;
}
