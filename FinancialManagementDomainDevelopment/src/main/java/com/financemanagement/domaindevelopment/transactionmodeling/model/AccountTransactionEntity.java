package com.financemanagement.domaindevelopment.transactionmodeling.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "transaction")
@Table(name = "TRANSACTION_MASTER")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@ToString(exclude = "transactionMasterEntity")
@DynamicInsert
@DynamicUpdate
public class AccountTransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	private String transactionId;

	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "TRNSCTN_ID", nullable = false)
	@MapsId
	private TransactionMasterEntity transactionMasterEntity;

	@Version
	private int version;

	@ElementCollection
	@CollectionTable(name = "ACCOUNT_TRANSACTION",
					joinColumns = @JoinColumn(name = "TRN_ID"
							      , referencedColumnName = "TRNSCTN_ID"))
	@MapKeyColumn(name = "ACCT_TRSCTN_TYPE")
	@MapKeyEnumerated(EnumType.STRING)
	@Builder.Default
	private Map<AccountTransactionType, AccountTransactionHelperEntity> accountTransactions = new HashMap<>();

}
