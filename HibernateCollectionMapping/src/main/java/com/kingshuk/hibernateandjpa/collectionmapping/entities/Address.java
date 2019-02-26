package com.kingshuk.hibernateandjpa.collectionmapping.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
	
	/*@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "addressSeq")
	@SequenceGenerator(name= "addressSeq", sequenceName = "address_profile_seq", allocationSize = 1)
	@Column(name="address_id")
	private long addressId;*/
	
	@Column(name = "address_line1", columnDefinition="VARCHAR2(60)")
	private String addressLine1;
	
	@Column(name = "address_line2", columnDefinition="VARCHAR2(40)")
	private String addressLine2;
	
	@Column(name = "city", columnDefinition="VARCHAR2(30)")
	private String city;
	
	@Column(name = "state", columnDefinition="VARCHAR2(10)")
	private String state;
	
	@Column(name = "zip_code", columnDefinition="VARCHAR2(6)")
	private String zipCode;

}
