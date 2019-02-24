package com.kingshuk.hibernatepractice.advanced.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "transaction")
@Table(name = "account_transaction")
@NoArgsConstructor
@Getter
@Setter
public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 25, name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSequenceGenerator")
	@GenericGenerator(name="transactionSequenceGenerator", 
	strategy="com.kingshuk.hibernatepractice.advanced.sequencegenerators.TransactionSequenceGenerator")
	private String transactionId;

	@Column(length = 1000, name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
	private Account account;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	private Category transactionCategory;
	
	
	/*
	 * I just found out that @Temporal can only be used
	 * with java.util.Date or java.sql.Date
	 */
	@Column(name = "transaction_date", columnDefinition="TIMESTAMP")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionDate;
	
	}
