package com.hibernatepractice.model.inheritance.singletable;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("BND")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BondStEntity extends InvestmentStEntity{
	
	@Column(name = "VALUE")
	private BigDecimal value;
	
	@Column(name = "INTRST_RT")
	private Double interestRate;
	
	@Column(name = "MATRTY_DT")
	private LocalDate maturityDate;
}
