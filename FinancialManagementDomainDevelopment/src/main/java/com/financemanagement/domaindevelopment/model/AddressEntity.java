package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.Builder;
import lombok.Data;



@Entity
@Table(name = "CUSTOMER_ADDRESS")
@Data
@Builder
public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4086699961105997705L;

	@Id
	@Column(name = "ADDRSS_ID", length = 20, updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSeqGen")
	@GenericGenerator(name = "addressSeqGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ADDRSS") })
	private String addressId;

	@Column(name = "ADDRSS_LN1", length = 150)
	private String addressLine1;

	@Column(name = "ADDRSS_LN2", length = 40)
	private String addressLine2;

	@Column(name = "CITY", length = 40)
	private String city;

	@Column(name = "STTE", length = 35)
	private String state;

	@Column(name = "ZIP", length = 10)
	private String zipCode;

	@ManyToMany(mappedBy = "addressList",
			cascade = { CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private Set<CustomerEntity> customersAtThisAddress;


}
