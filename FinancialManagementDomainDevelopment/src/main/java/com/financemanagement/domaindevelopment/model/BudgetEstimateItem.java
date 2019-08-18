package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.BudgetEstimateItemStatus;
import com.financemanagement.domaindevelopment.enums.Currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BUDGET_ESTIMATE_ITEM")
@NoArgsConstructor
@Getter
@Setter
public class BudgetEstimateItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3685081968601619498L;
	
	@Id
	@Column(length = 20, name = "BDGT_ITM_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSequenceGen")
	@GenericGenerator(name = "accSequenceGen", 
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.AccountSequenceGenerator")
	private String budgetItemId;
	
	@Column(length = 1000, name = "BDGT_ITM_DSCRPTN")
	private String description;
	
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
	
	

}
