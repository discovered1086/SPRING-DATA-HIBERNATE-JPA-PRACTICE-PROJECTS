package com.financemanagement.domaindevelopment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.financemanagement.domaindevelopment.enums.WalletAccountType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "WALLET_ACCOUNT")
@Getter
@Setter
public class WalletAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "ACCT_TYP")
	private WalletAccountType walletAccountType;

	@Column(name = "ACC_PRVDR", length = 40)
	private String accountProvider;
	
	@Column(name="ACCT_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double accountBalance;

}
