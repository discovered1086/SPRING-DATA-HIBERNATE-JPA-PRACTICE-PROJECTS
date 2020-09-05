package com.hibernatepractice.model.inheritance.mappedsuperclass;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

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
@MappedSuperclass
public abstract class InvestmentEntity {
	
	@Column(name = "INV_NAME")
	private String investmentName;
	
	@Column(name = "ISSUER")
	protected String issuer;
	
	@Column(name = "PURCHASE_DATE")
	protected LocalDate purchaseDate;

}
