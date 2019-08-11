package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction_category")
@Getter
@Setter
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 20, name = "category_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequenceGen")
	@GenericGenerator(name = "categorySequenceGen", 
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CategorySequenceGenerator")
	private String categoryId;

	@Column(length = 60, name = "CTGRY_NM")
	private String categoryName;

	@Column(length = 100, name = "CTGRY_DESC")
	private String categoryDesc;
	
	@Column(length = 60, name = "SB_CTGRY_NM")
	private String subCategoryName;

	@Column(length = 100, name = "SB_CTGRY_DESC")
	private String subCategoryDescription;
	
	@Column(name="CTGRY_EFFCTV_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime categoryEffectiveDate;
	
	@Column(name="CTGRY_TRMNTN_DT")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime categoryTerminationDate;

}
