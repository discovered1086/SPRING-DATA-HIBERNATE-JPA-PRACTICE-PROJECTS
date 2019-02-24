package com.kingshuk.hibernatepractice.advanced.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	strategy = "com.kingshuk.hibernatepractice.advanced.sequencegenerators.CategorySequenceGenerator")
	private String categoryId;

	@Column(length = 60, name = "category_name")
	private String categoryName;

	@Column(length = 100, name = "category_description")
	private String categoryDesc;

}
