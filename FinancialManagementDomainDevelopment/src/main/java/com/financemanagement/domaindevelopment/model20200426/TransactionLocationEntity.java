package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "transactionLocation")
@Table(name = "TRANSACTION_LOCATION")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@DynamicInsert(true)
@DynamicUpdate(true)
public class TransactionLocationEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5251215271446684784L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	private String transactionId;
	
	@Column(name = "TNSCTN_TMZN_ID",length = 20)
	private String timeZoneId;
	
	@Column(name = "TNSCTN_CTY",length = 50)
	private String city;
	
	@Column(name = "TNSCTN_RGN",length = 50)
	private String region;
	
	@OneToOne
	@JoinColumn(name = "CNTRY_CD", referencedColumnName = "CNTRY_CD")
	private CountryEntity country;

}
