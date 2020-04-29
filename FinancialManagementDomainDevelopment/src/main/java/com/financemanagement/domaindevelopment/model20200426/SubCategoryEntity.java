package com.financemanagement.domaindevelopment.model20200426;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.financemanagement.domaindevelopment.allmodels.sequencegenerators.CommonSequenceGenerator;
import com.openpojo.business.BusinessIdentity;
import com.openpojo.business.annotation.BusinessKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FINANCE_SUB_CATEGORY")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "findExistingSubCategoryByName", query = "from SubCategoryEntity c where upper(trim(c.subCategoryName))=upper(trim(:subCategoryNameInput))")

public class SubCategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 15, name = "SB_CTGRY_ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategorySequenceGen")
	@GenericGenerator(name = "subCategorySequenceGen", strategy = "com.financemanagement.transaction.persistence.entities.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "SBCTGRY") })
	private String subCategoryId;

	@Version
	private int version;

	@Column(length = 60, name = "SB_CTGRY_NM", nullable = false)
	@NotBlank(message = "Sub category name must not be blank")
	@BusinessKey(required = true)
	private String subCategoryName;

	@Column(length = 100, name = "SB_CTGRY_DESC")
	private String subCategoryDescription;

	@ManyToOne
	@JoinColumn(name = "CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	@BusinessKey(required = true)
	private CategoryEntity parentCategory;

	@Column(name = "SB_CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime subCategoryEffectiveDate;

	@Column(name = "SB_CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime subCategoryTerminationDate;

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
