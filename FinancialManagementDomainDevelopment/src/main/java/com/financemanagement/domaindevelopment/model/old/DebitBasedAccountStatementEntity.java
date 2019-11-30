package com.financemanagement.domaindevelopment.model.old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DEBIT_ACCONT_STATEMENT")
@Getter
@Setter
public class DebitBasedAccountStatementEntity extends AccountStatementSummaryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556955442948517767L;
	
	
	
	@Column(name="OPNNG_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double openingBalance;
	
	@Column(name="CLSNG_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double closingBalance;

}
