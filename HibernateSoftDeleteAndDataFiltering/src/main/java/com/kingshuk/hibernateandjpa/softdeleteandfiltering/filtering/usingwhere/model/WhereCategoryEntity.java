package com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingwhere.model;

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

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "WhereCategory")
@Table(name = "FINANCE_CATEGORY_WHERE")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE FINANCE_CATEGORY_WHERE SET CTGRY_TRMNTN_DT = CURRENT_TIMESTAMP WHERE ctgry_id=?"
			, check = ResultCheckStyle.COUNT)
@NamedQuery(name = "findCategoryByWhereName", query = "from WhereCategory c where upper(trim(c.categoryName)) like upper(trim(:categoryNameInput))"
		+ " and trunc(c.categoryEffectiveDate) <= trunc(sysdate)")
@Where(clause = "NVL(CTGRY_TRMNTN_DT, CURRENT_TIMESTAMP+1) > CURRENT_TIMESTAMP")
public class WhereCategoryEntity implements Serializable {

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
