package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.AccountFeeFrequency;
import com.financemanagement.domaindevelopment.enums.AccountFeeType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCT_FEES")
@Getter
@Setter
public class AccountFees implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5667101048887987393L;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FEE_TYP")
	private AccountFeeType feeType;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FEE_FRQNCY")
	private AccountFeeFrequency feeFrequency;
	
	@Column(name="FEE_AMT", columnDefinition = "NUMBER(20,2)" )
	private double feeAmount;
	
	@Column(name="FEE_EFFCTV_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime feeEffectiveDate;
	
	@Column(name="FEE_TRMNTN_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime feeTerminationDate;

}
