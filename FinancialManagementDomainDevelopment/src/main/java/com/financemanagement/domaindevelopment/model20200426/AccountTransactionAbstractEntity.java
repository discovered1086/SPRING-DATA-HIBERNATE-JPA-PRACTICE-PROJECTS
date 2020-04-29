package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "account_transaction")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AccountTransactionAbstractEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -284020251814718938L;
	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	private String transactionId;

}
