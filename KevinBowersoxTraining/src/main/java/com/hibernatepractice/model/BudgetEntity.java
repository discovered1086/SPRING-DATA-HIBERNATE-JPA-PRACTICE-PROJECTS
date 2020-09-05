package com.hibernatepractice.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BUDGET")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGen")
	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", 
		pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "BUDGET_ID")
	private long budgetId;

	@Column(name = "BUDGET_NAME")
	private String name;

	@Column(name = "GOAL_AMOUNT")
	private BigDecimal goalAmount;

	@Column(name = "BUDGET_PERIOD")
	private String period;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BUDGET_TRANSACTION", 
			joinColumns = @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), 
			inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "TRANSACTION_ID"))
	private List<TransactionEntity> transactions;
}
