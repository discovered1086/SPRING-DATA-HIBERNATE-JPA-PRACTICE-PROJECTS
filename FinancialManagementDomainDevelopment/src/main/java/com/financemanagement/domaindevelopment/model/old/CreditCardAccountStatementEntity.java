package com.financemanagement.domaindevelopment.model.old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CREDIT_CARD_ACCOUNT_STATEMENT")
@Getter
@Setter
public class CreditCardAccountStatementEntity extends CreditBasedAccountStatementEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429470525693291853L;

	@Column(name="STMNT_BLNC", columnDefinition = "NUMBER(20,2)" )
	private double statementBalance;
	
	@Column(name="MIN_PYMNT_DUE", columnDefinition = "NUMBER(20,2)" )
	private double minimumPaymentDue;

}
