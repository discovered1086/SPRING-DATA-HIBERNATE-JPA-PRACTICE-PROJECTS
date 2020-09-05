package com.hibernatepractice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "COMPLEX_ACCOUNT_USER")
@Data
@EqualsAndHashCode(exclude = {"credential", "accounts"})
@Builder
@Access(AccessType.FIELD)
public class ComplexUser {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userTableGen")
	@TableGenerator(name = "userTableGen", table = "USER_TABLE_GENERATOR", pkColumnName = "IDENTIFIER_NAME", valueColumnName = "IDENTIFIER_VALUE")
	@Column(name = "USER_ID")
	private long userId;

	@Column(name = "USER_NAME")
	@Basic(optional = false)
	private String userName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dob;
	
	@ManyToMany(mappedBy = "users")
	private Set<FinancialAccountEntity> accounts;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private Credential credential;
		
	@Transient
	private List<String> phoneNumbers;
	
	@Embedded
	private Address address;

	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "LAST_UPDATED_DATE", insertable = false)
	private LocalDateTime lastUpdatedDate;

	@Formula("TRUNC((SYSDATE - DATE_OF_BIRTH)/ 365.25)")
	private int age;

	@Override
	public String toString() {
		return new StringBuilder().append("\n\n\n").append("The user entity: \n")
				.append("====================================\n").append("user id = ").append(userId).append("\n")
				.append("username = ").append(userName).append("\n").append("first name = ").append(firstName)
				.append("\n").append("last name = ").append(lastName).append("\n").append("Date of Birth = ")
				.append(dob).append("\n").append("createdDate = ").append(createdDate.toString()).append("\n")
				.append("last updated date = ")
				.append(lastUpdatedDate!=null?lastUpdatedDate.toString():"Not available").append("\n").append("age = ")
				.append(age).append("\n").append("====================================").append("\n\n\n").toString();
	}

}
