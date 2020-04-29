package com.financemanagement.domaindevelopment.model20200426;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "regularAccountTransaction")
@Table(name = "REGULAR_ACCOUNT_TRANSACTION")
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@DynamicInsert(true)
@DynamicUpdate(true)
public class RegularAccountTransactionEntity extends AccountTransactionAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5626038658315563877L;
	
	@Embedded
	private AccountTransactionDetailsEntity accountDetails;

}
