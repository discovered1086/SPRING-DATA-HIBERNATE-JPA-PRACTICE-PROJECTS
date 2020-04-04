package com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingfilters.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.ParamDef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Category")
@Table(name = "FINANCE_CATEGORY")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "findExistingCategoryByName", query = "from Category c where upper(trim(c.categoryName)) like upper(trim(:categoryNameInput))"
		+ " and trunc(c.categoryEffectiveDate) <= trunc(sysdate)")
@FilterDef(name = "effTermDateCheck", parameters = {
		@ParamDef(name = "effDate", type = "org.hibernate.type.OffsetDateTimeType"),
		@ParamDef(name = "termDate", type = "org.hibernate.type.OffsetDateTimeType") })
@Filter(name = "effTermDateCheck", condition = "CTGRY_EFFCTV_DT <= :effDate and "
		+ "NVL(CTGRY_TRMNTN_DT, SYSTIMESTAMP+1) > :termDate")
public class CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 15, name = "CTGRY_ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catSequenceGen")
	@SequenceGenerator(name = "catSequenceGen", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
	private long categoryId;

	@Version
	private int version;

	@Column(length = 60, name = "CTGRY_NM")
	@NotBlank(message = "Category name must not be blank")
	@NaturalId(mutable = true)
	private String categoryName;

	@Column(length = 100, name = "CTGRY_DESC")
	private String categoryDescription;

	@Column(name = "CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime categoryEffectiveDate;

	@Column(name = "CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime categoryTerminationDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "CTGRY_TRN_TYP")
	@NotNull(message = "Transaction type must not be empty")
	private TransactionTypeEnum categoryTransactionType;

}
