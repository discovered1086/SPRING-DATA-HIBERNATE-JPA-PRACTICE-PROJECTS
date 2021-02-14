package com.financemanagement.domaindevelopment.v1.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.v1.model.enums.BudgetEstimateItemStatus;
import com.financemanagement.domaindevelopment.v1.model.enums.Currency;
import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BUDGET_ESTIMATE_ITEM")
@NoArgsConstructor
@Getter
@Setter
public class BudgetEstimateItemEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3685081968601619498L;
	
	@Id
	@Column(length = 40, name = "BDGT_ITM_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetItemSequenceGen")
	@GenericGenerator(name = "budgetItemSequenceGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "BDGTITM") })
	private String budgetItemId;
	
	@Column(length = 1000, name = "BDGT_ITM_DSCRPTN")
	private String budgetItemDescription;
	
	@Enumerated(EnumType.STRING)
	@Column(name="BDGT_ITM_CRRNCY")
	private Currency budgetItemCurrency;
	
	@Column(name="BDGT_ITM_EST_AMT", columnDefinition = "NUMBER(20,2)" )
	private double budgetItemEstimateAmount;
	
	@Column(name="BDGT_ITM_ACT_AMT", columnDefinition = "NUMBER(20,2)" )
	private double budgetItemActualAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="BDGT_ITM_STTS")
	private BudgetEstimateItemStatus budgetEstimateItemStatus;
	
	@Column(name = "BDGT_ENTRY_DT", columnDefinition="TIMESTAMP")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime budgetEstimateItemEntryDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BDGT_CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	private CategoryEntity budgetEstimateItemCategory;

}
