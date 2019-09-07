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
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FINANCE_CATEGORY")
@Getter
@Setter
public class CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 15, name = "CTGRY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequenceGen")
	@GenericGenerator(name = "categorySequenceGen",
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "CTGRY") })
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
