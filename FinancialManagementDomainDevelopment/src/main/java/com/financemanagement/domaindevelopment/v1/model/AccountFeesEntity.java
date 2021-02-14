package com.financemanagement.domaindevelopment.v1.model;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;
import com.financemanagement.domaindevelopment.v1.model.enums.AccountFeeFrequency;
import com.financemanagement.domaindevelopment.v1.model.enums.AccountFeeType;
import com.financemanagement.domaindevelopment.v1.model.enums.Currency;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "CUSTOMER_ACCOUNT_FEES")
@Getter
@Setter
public class AccountFeesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5667101048887987393L;
	
	@Id
	@Column(length = 15, name = "ACCT_FEE_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accFeeSequenceGen")
	@GenericGenerator(name = "accFeeSequenceGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ACCTFEE")})
	private String accountFeeId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FEE_TYP")
	private AccountFeeType feeType;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FEE_FRQNCY")
	private AccountFeeFrequency feeFrequency;
	
	@Column(name="FEE_AMT", columnDefinition = "NUMBER(20,2)" )
	private double feeAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="FEE_CRNCY")
	private Currency feeCurrency;
	
	@Column(name="FEE_EFFCTV_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime feeEffectiveDate;
	
	@Column(name="FEE_TRMNTN_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime feeTerminationDate;
	
	

}
