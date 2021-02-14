package com.financemanagement.domaindevelopment.transactionmodeling.model.v3;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "transactionLocation")
@Table(name = "TRANSACTION_LOCATION_V3")
@NoArgsConstructor
@Builder
@Data
@ToString(exclude = "transactionMasterEntity")
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class TransactionLocationEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5251215271446684784L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID", nullable = false)
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRNSCTN_ID", nullable = false)
	@MapsId
	private TransactionMasterEntity transactionMasterEntity;

}
