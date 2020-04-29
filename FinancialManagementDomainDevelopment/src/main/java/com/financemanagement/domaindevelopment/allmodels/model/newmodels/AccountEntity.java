package com.financemanagement.domaindevelopment.allmodels.model.newmodels;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.allmodels.sequencegenerators.CommonSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "CUSTOMER_ACCOUNT")
@ToString
@EqualsAndHashCode(exclude = "accountId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540097108451574865L;

	@Id
	@Column(length = 20, name = "ACCT_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSequenceGen")
	@GenericGenerator(name = "accSequenceGen", strategy = "com.financemanagement.domaindevelopment.allmodels.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ACCT") })
	private String accountId;

	@Column(length = 50, name = "ACCT_NMBR")
	private String accountNumber;

	@ManyToOne
	@JoinColumn(name = "ACC_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	private CurrencyEntity currency;

	@Column(length = 100, name = "ACCT_TTLE")
	private String accountDescription;

	@Column(name = "ACC_OPN_DT", columnDefinition = "TIMESTAMP")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime accountOpenningDate;

	@Column(name = "ACC_CLS_DT", columnDefinition = "TIMESTAMP")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime accountClosingDate;

}
