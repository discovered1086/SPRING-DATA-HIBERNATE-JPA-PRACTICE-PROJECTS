package com.kingshuk.hibernateandjpa.hibernatebeanvalidation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540097108451574865L;

	@Id
	@Column(length = 20, name = "account_id", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSequenceGen")
	@SequenceGenerator(name = "accSequenceGen", 
			sequenceName = "ACCOUNT_SEQ",allocationSize = 1)
	private long accountId;

	@Column(length = 20, name = "account_number")
	@Size(max = 20, message = "Hey yo..!! account number should be less than 20 bro..")
	@NotBlank
	private String accountNumber;
	
	@Column(length = 100, name = "account_name")
	private String accountName;

	@Column(length = 100, name = "account_title")
	private String accountDesc;
	
}
