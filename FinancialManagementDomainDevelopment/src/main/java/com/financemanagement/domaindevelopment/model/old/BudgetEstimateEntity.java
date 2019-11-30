package com.financemanagement.domaindevelopment.model.old;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BUDGET_ESTIMATE")
@NoArgsConstructor
@Getter
@Setter
public class BudgetEstimateEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 3099999947097457703L;
	
	@Id
	@Column(length = 20, name = "BDGT_EST_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetSequenceGen")
	@GenericGenerator(name = "budgetSequenceGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "BDGT") })
	private String budgetEstimateId;
	
	@Column(name="BDGT_STRT_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime budgetPeriodStartDate;
	
	@Column(name="BDGT_END_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime budgetPeriodEndDate;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "BUDGET_ITEM_MAPPING",
			joinColumns=@JoinColumn(name = "BDGT_ID", referencedColumnName = "BDGT_EST_ID"),
			inverseJoinColumns = @JoinColumn(name = "BDGT_ITM_ID", referencedColumnName = "BDGT_ITM_ID"))
	private List<BudgetEstimateItemEntity> budgetEstimateItems;
	

}
