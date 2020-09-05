package com.hibernatepractice.model.inheritance.singletable;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "portfolio")
@SuperBuilder
@Entity
@Table(name = "INVESTMENT_ST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INVESTMENT_TYPE")
public abstract class InvestmentStEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGen")
	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", 
			pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "INVESTMENT_ID")
	private long investmentId;
	
	@Column(name = "INV_NAME")
	private String investmentName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PORTFOLIO_ID", referencedColumnName = "PORTFOLIO_ID")
	private PortfolioStEntity portfolio;
	
	@Column(name = "ISSUER")
	protected String issuer;
	
	@Column(name = "PURCHASE_DATE")
	protected LocalDate purchaseDate;
	
}
