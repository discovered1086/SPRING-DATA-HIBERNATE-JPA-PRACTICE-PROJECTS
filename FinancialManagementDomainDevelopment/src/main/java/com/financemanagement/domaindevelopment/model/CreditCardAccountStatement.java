package com.financemanagement.domaindevelopment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CRDT_CRD_STMNT")
@Getter
@Setter
public class CreditCardAccountStatement extends CreditBasedAccountStatement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429470525693291853L;
	
	@Column(name="MIN_PYMNT_DUE", columnDefinition = "NUMBER(20,2)" )
	private double minimumPaymentDue;

}
