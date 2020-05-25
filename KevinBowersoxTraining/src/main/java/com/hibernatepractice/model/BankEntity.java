package com.hibernatepractice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "FINANCIAL_ORGANIZATION")
@Data
@Builder
@Access(AccessType.FIELD)
public class BankEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGen")
	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", 
					pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "BANK_ID")
	private long bankId;

	@Column(name = "BANK_NAME")
	@Basic(optional = false)
	private String bankName;

	@Column(name = "IS_INTERNATIONAL")
	private boolean isInternational;
	
	@ElementCollection
	@CollectionTable(name = "BANK_CONTACT", joinColumns = @JoinColumn(name = "BANK_ID"))
	@Column(name = "CONTACT_NAME", nullable = false, unique = true)
	private List<String> contactNames;
	
	@ElementCollection
	@CollectionTable(name = "BANK_LOCATIONS", joinColumns = @JoinColumn(name = "BANK_ID"))
	@MapKeyColumn(name = "LOCATION_CODE", nullable = false, unique = true, updatable = false)
	@Column(name = "LOCATION", nullable = false, unique = true)
	private Map<String, String> locations;

	@ElementCollection
	@CollectionTable(name = "BANK_ADDRESS", joinColumns = @JoinColumn(name = "BANK_ID"))
	@AttributeOverrides({ 
			@AttributeOverride(name = "addressLine1", column = @Column(name = "BANK_ADDR_LN1")),
			@AttributeOverride(name = "addressLine2", column = @Column(name = "BANK_ADDR_LN2")),
			@AttributeOverride(name = "city", column = @Column(name = "BANK_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "BANK_STATE")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "BANK_ZIP")) })
	private List<Address> address;

	@Column(name = "ESTABLISHED_DATE", updatable = false)
	private LocalDate establishedDate;

}
