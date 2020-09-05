package com.hibernatepractice.model.inheritance.mappedsuperclass;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "STOCK")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity extends InvestmentEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGen")
	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", 
			pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "STOCK_ID")
	private long stockId;
	
	@Column(name = "SHARE_PRICE")
	private BigDecimal sharePrice;

	@Column(name = "QUANTITY")
	private int quantity;

}
