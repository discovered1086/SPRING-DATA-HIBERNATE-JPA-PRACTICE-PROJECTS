package com.financemanagement.domaindevelopment.transactionmodeling.model.v3;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CURRENCY_V3")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "CNTRY_CD", referencedColumnName = "CNTRY_CD")
	private CountryEntity country;
	
}
