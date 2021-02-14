package com.financemanagement.domaindevelopment.v1.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INTEREST_CREDIT_ACCOUNT")
@Getter
@Setter
public class InterestOnCreditAccountEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5174705616913216534L;
	
	@Id
	@Column(length = 20, name = "INTRST_RCRD_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interestSeqGen")
	@GenericGenerator(name = "interestSeqGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "INTRST") })
	private String interestRecordId;
	
	@Column(name="INTRST_RTE", columnDefinition = "NUMBER(10,2)" )
	private double interestRate;
	
	@Column(name="INTRST_RTE_EFFCTV_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime interestRateEffectiveDate;
	
	@Column(name="INTRST_RTE_TRMNTN_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime interestRateTerminationDate;

}
