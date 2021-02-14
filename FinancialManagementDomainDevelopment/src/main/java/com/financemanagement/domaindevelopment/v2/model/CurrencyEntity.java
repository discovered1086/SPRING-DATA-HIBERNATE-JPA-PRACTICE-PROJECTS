package com.financemanagement.domaindevelopment.v2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CURRENCY_V2")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7398817190407696735L;

	@Id
	@Column(name="CRRNCY_CD", length = 10)
	private String currencyCode;
	
	@Column(name = "CRRNCY_NMRC_CD", length = 15)
	private String numericCode;
	
	@Column(name = "CRRNCY_DSCRPTN", length = 100)
	private String description;
	
	@Column(name = "CRRNCY_CNTRY", length = 100)
	private String country;
	
}
