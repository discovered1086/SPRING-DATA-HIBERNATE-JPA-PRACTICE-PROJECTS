package com.financemanagement.domaindevelopment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LOAN_ACCOUNT_STATEMENT")
@Getter
@Setter
public class LoanAccountStatement extends CreditBasedAccountStatement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429470525693291853L;

	@Column(name="PY_OFF_AMT", columnDefinition = "NUMBER(20,2)" )
	private double payOffAmount;
	
	@Column(name="MIN_PYMNT_DUE", columnDefinition = "NUMBER(20,2)" )
	private double minimumPaymentDue;

}
