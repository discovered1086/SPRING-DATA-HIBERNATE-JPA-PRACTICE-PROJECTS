package com.financemanagement.domaindevelopment.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="LOAN_ACCOUNT")
@Getter
@Setter
public class LoanAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
