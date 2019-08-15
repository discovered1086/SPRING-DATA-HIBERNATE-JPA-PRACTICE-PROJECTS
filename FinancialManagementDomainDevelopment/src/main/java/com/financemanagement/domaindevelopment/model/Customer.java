package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267904589383486101L;

	@Id
	@Column(length = 20, name = "CSTMR_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeqGen")
	@GenericGenerator(name = "customerSeqGen", 
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CustomerSequenceGenerator")
	private String customerId;
	
	@Column(length = 40, name = "FRST_NM")
	private String firstName;

	@Column(length = 40, name = "LST_NM")
	private String lastName;

	@Column(length = 75, name = "EML_ADDRSS")
	private String email;

	@Column(length = 20, name = "CNTCT_NMBR")
	private String phoneNumber;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
							CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "CUSTOMER_ADDRESS_MAPPING", joinColumns = {
			@JoinColumn(name = "customer_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
	private Set<Address> addressList;

}
