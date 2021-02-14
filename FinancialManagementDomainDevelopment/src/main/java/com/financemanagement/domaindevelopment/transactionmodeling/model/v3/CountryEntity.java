package com.financemanagement.domaindevelopment.transactionmodeling.model.v3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "COUNTRY_V3")
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
	private String countryCode;

	@Column(name = "CNTRY_NM", length = 50)
	private String countryName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	private Set<CurrencyEntity> currencies;

	@Column(name = "CNTRY_CPTL", length = 100)
	private String capital;

	@Column(name = "CNTRY_ISO_CD", length = 3)
	private String isoAlpha3;

	@Column(name = "CNTRY_GEO_NM_ID", length = 20)
	private String geoNameId;

}
