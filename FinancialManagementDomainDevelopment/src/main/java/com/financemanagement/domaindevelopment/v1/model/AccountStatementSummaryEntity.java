package com.financemanagement.domaindevelopment.v1.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNT_STATEMENT_SUMMARY")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountStatementSummaryEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -864888275514254560L;
	
	@Id
	@Column(length = 30, name = "ACCT_STMNT_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accStatementSequenceGen")
	@GenericGenerator(name = "accStatementSequenceGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "STMNT") })
	private String accountStatementId;
	
	@Column(name="STMNT_STRT_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime statementPeriodStartDate;
	
	@Column(name="STMNT_END_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime statementPeriodEndDate;
	
	@Column(name="STMNT_GNRTN_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime statementGenerationDate;

}
