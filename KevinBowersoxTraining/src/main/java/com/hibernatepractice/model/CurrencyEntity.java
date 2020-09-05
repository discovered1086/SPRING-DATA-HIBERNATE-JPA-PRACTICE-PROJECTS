package com.hibernatepractice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CURRENCY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CurrencyKey.class)
public class CurrencyEntity {

	@Id
	@Column(name = "CRR_NM")
	private String name;
	
	@Id
	@Column(name = "CNTRY_NM")
	private String countryName;
	
	@Column(name = "SYM")
	private String symbol;
}
