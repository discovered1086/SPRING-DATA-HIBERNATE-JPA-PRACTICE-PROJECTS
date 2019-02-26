package com.kingshuk.hibernateandjpa.collectionmapping.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Role {
	
	public enum RoleName{
		ADMINISTRATOR,
		CUSTOMER,
		SALES,
		CUSTOMER_SERVICE
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="role_name", columnDefinition="VARCHAR2(20)")
	private RoleName roleName;
	
	@Column(name="role_desc", columnDefinition="VARCHAR2(60)")
	private String roleDescription;

}
