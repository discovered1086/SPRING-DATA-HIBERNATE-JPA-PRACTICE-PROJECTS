package com.financemanagement.domaindevelopment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DEBIT_ACCT_STMNT")
@Getter
@Setter
public class DebitBasedAccountStatement extends AccountStatement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556955442948517767L;
	
	@Column(name="AVLBLE_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double availableBalance;

}
