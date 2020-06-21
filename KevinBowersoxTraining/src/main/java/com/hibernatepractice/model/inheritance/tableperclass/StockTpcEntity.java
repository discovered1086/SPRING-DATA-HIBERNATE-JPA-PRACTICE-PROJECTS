package com.hibernatepractice.model.inheritance.tableperclass;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "STOCK_TPC")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StockTpcEntity extends InvestmentTpcEntity{
		
	@Column(name = "SHARE_PRICE")
	private BigDecimal sharePrice;

	@Column(name = "QUANTITY")
	private int quantity;

}
