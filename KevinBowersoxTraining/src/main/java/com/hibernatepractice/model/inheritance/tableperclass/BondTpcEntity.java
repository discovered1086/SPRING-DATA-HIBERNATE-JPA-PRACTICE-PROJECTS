package com.hibernatepractice.model.inheritance.tableperclass;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "BOND_TPC")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BondTpcEntity extends InvestmentTpcEntity{
	
	@Column(name = "VALUE")
	private BigDecimal value;
	
	@Column(name = "INTRST_RT")
	private Double interestRate;
	
	@Column(name = "MATRTY_DT")
	private LocalDate maturityDate;
}
