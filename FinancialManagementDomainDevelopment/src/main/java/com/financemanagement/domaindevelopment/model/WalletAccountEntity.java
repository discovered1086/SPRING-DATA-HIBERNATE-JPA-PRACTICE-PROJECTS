package com.financemanagement.domaindevelopment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.financemanagement.domaindevelopment.enums.WalletAccountType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "WALLET_ACCOUNT")
@Getter
@Setter
public class WalletAccountEntity extends AccountEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ACCT_TYP")
	private WalletAccountType walletAccountType;
	
	@Column(name = "ACC_NM", length = 40)
	private String accountName;

	@Column(name = "ACC_PRVDR", length = 40)
	private String accountProvider;
	
	@Column(name="AVLBLE_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double availableBalance;
	
	
	@Column(name = "ACCT_STMNT_ID", length = 30)
	private String accountStatementId;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "ACCT_STMNT_ID", referencedColumnName = "ACCT_STMNT_ID")
	private List<DebitBasedAccountStatementEntity> accountStatements;

}
