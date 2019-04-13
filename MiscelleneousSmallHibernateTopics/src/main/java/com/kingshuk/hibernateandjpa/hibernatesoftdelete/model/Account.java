package com.kingshuk.hibernateandjpa.hibernatesoftdelete.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Entity
@NoArgsConstructor
@Slf4j
@AllArgsConstructor
@Table(name = "account")
@SQLDelete(sql = "UPDATE account SET acc_stts = 'DELETED' where account_id=?", 
		   check = ResultCheckStyle.COUNT)
@Where(clause = "acc_stts NOT IN ('DELETED', 'SUSPENDED')")
@NamedQuery(name="Account.FindByAccountName",
		query ="SELECT a FROM Account a WHERE a.accountName like :accountName")
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
	private String accountNumber;
	
	@Column(length = 100, name = "account_name")
	private String accountName;

	@Column(length = 100, name = "account_title")
	private String accountDesc;
	
	@Column(name="ACC_STTS", columnDefinition = "VARCHAR2(10)")
	@Enumerated(EnumType.STRING)
	private AccountState accountState;
	
	@PreRemove
	public void preDeleteMethod() {
		log.info("Set state to DELETED");
		this.accountState = AccountState.DELETED;
	}
}
