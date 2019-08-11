package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCT_STMNT")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class AccountStatement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -864888275514254560L;
	
	@Id
	@Column(length = 20, name = "ACC_STMNT_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSequenceGen")
	@GenericGenerator(name = "accSequenceGen", 
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.AccountSequenceGenerator")
	private String accountStatementId;
	
	@Column(name="STMNT_STRT_DT")
	@Temporal(TemporalType.DATE)
	private ZonedDateTime statementPeriodStartDate;
	
	@Column(name="STMNT_END_DT")
	@Temporal(TemporalType.DATE)
	private ZonedDateTime statementPeriodEndDate;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ACCT_ID", referencedColumnName = "ACCT_ID", nullable=false)
	private Account account;

}
