package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	@Column(length = 20, name = "customer_id", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeqGen")
	@GenericGenerator(name = "customerSeqGen", 
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CustomerSequenceGenerator")
	private String customerId;

	@Column(length = 40, name = "first_name")
	private String firstName;

	@Column(length = 40, name = "last_name")
	private String lastName;

	@Column(length = 75, name = "email_address")
	private String email;

	@Column(length = 20, name = "contact_number")
	private String phoneNumber;

	@OneToMany(cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, mappedBy = "accountHolder", fetch = FetchType.LAZY)
	private List<Account> customerAccounts;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
							CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "CUSTOMER_ADDRESS_MAPPING", joinColumns = {
			@JoinColumn(name = "customer_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
	private Set<Address> addressList;

}
