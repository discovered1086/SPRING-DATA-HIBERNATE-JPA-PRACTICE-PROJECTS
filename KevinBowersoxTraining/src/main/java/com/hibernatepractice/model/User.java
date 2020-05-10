package com.hibernatepractice.model;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "ACCOUNT_USER")
@Data
@Builder
@Access(AccessType.FIELD)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accUserSequence")
	@SequenceGenerator(name = "accUserSequence", 
					   sequenceName = "ACCOUNT_USER_SEQUENCE", 
					   initialValue = 1, 
					   allocationSize = 1)
	@Column(name = "USER_ID")
	private long userId;

	@Column(name = "USER_NAME")
	@Basic(optional = false)
	private String userName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "LAST_UPDATED_DATE", insertable = false)
	private LocalDateTime lastUpdatedDate;
}
