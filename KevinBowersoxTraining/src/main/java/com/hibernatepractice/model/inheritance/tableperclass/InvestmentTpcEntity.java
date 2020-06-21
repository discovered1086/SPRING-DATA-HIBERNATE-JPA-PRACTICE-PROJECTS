package com.hibernatepractice.model.inheritance.tableperclass;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class InvestmentTpcEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", 
//			pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "INVESTMENT_ID")
	private long investmentId;
	
	@Column(name = "INV_NAME")
	private String investmentName;
	
	@Column(name = "ISSUER")
	protected String issuer;
	
	@Column(name = "PURCHASE_DATE")
	protected LocalDate purchaseDate;

}
