package com.hibernatepractice.model.inheritance.tableperclass;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PORTFOLIO_TPC")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioTpcEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGen")
	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", 
			pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "PORTFOLIO_ID")
	private Long portfolioId;

	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
	private List<InvestmentTpcEntity> investements;
	

}
