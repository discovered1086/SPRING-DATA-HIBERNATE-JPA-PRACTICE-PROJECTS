package com.hibernatepractice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hibernatepractice.model.inheritance.mappedsuperclass.InvestmentEntity;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PORTFOLIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioEntity {

	@Id
	@GeneratedValue
	@Column(name = "PORTFOLIO_ID")
	private Long portfolioId;

	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy = "portfolio")
	private List<InvestmentEntity> investements;

}
