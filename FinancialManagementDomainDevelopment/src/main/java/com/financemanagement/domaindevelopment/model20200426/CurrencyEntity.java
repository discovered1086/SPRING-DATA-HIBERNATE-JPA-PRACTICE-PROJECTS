package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CURRENCY")
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
	@BusinessKey(required = true)
	private String currencyCode;
	
	@Column(name = "CRRNCY_NMRC_CD", length = 15)
	@BusinessKey(required = true)
	private String numericCode;
	
	@Column(name = "CRRNCY_DSCRPTN", length = 100)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "CNTRY_CD", referencedColumnName = "CNTRY_CD")
	private CountryEntity country;

	@Override
	public int hashCode() {
		return BusinessIdentity.getHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return BusinessIdentity.areEqual(this, obj);
	}

	@Override
	public String toString() {
		return BusinessIdentity.toString(this);
	}
	
	
	
}
