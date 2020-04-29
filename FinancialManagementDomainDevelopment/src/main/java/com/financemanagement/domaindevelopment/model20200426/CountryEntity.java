package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COUNTRY")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3662984972170102479L;

	@Id
	@Column(name = "CNTRY_CD", length = 20)
	@BusinessKey(required = true)
	private String countryCode;

	@Column(name = "CNTRY_NM", length = 50)
	private String countryName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	private Set<CurrencyEntity> currencies;

	@Column(name = "CNTRY_CPTL", length = 100)
	private String capital;

	@Column(name = "CNTRY_ISO_CD", length = 3)
	@BusinessKey(required = false)
	private String isoAlpha3;

	@Column(name = "CNTRY_GEO_NM_ID", length = 20)
	@BusinessKey(required = false)
	private String geoNameId;
	
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
