package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.financemanagement.domaindevelopment.allmodels.sequencegenerators.CommonSequenceGenerator;
import com.financemanagement.domaindevelopment.enums.newenums.TransactionTypeEnum;
import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

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
@NamedQuery(name = "findExistingCategoryByName", query = "from Category c where upper(trim(c.categoryName))=upper(trim(:categoryNameInput))"
		+ " and trunc(c.categoryEffectiveDate) <= trunc(sysdate)")
@FilterDef(name = "effTermDateCheck", parameters = {
		@ParamDef(name = "effDate", type = "org.hibernate.type.ZonedDateTimeType"),
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequenceGen")
	@GenericGenerator(name = "categorySequenceGen", strategy = "com.financemanagement.transaction.persistence.entities.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "CTGRY") })
	private String categoryId;

	@Version
	private int version;

	@Column(length = 60, name = "CTGRY_NM", nullable = false, unique = true)
	@NotBlank(message = "Category name must not be blank")
	@NaturalId(mutable = true)
	@BusinessKey(required = true)
	private String categoryName;

	@Column(length = 100, name = "CTGRY_DESC")
	private String categoryDescription;

	@Column(name = "CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime categoryEffectiveDate;

	@Column(name = "CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime categoryTerminationDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "CTGRY_TRN_TYP")
	@NotNull(message = "Transaction type must not be empty")
	private TransactionTypeEnum categoryTransactionType;

	// @ManyToMany(cascade = {CascadeType.MERGE })
	@OneToMany(mappedBy = "parentCategory", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private Set<SubCategoryEntity> subCategoryEntities;

	@Override
	public int hashCode() {
		return BusinessIdentity.getHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return BusinessIdentity.areEqual(this, obj);
	}

	@Override
	public String toString() {
		return BusinessIdentity.toString(this);
	}
}
