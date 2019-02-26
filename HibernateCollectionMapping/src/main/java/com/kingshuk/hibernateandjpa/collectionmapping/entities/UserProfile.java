package com.kingshuk.hibernateandjpa.collectionmapping.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name="user_profile")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "userProfileSeq")
	@SequenceGenerator(name= "userProfileSeq", sequenceName = "user_profile_sequence", allocationSize = 1)
	private long id;
	
	@Column(name= "user_id", columnDefinition="VARCHAR2(20)",unique=true, nullable=false)
	@NotNull(message = "User ID can't be null")
	@Pattern(regexp = "[a-z A-Z 0-9]", message = "User ID must not have any special characters")
	private String userId;
	
	@Column(name = "email_address", columnDefinition="VARCHAR2(60)")
	private String emailAddress;
	
	@Embedded
	private Address address;
	
	@ElementCollection
	@CollectionTable(name="user_role", joinColumns = {@JoinColumn(name= "user_profile_id")})
	private Set<Role> roles = new HashSet<>();

}
