package com.financemanagement.domaindevelopment.mainclass.model.newmodels;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.financemanagement.domaindevelopment.mainclass.deserialization.DateTimeDeSerializer;
import com.financemanagement.domaindevelopment.mainclass.deserialization.DateTimeSerializer;
import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

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
public class SubCategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 15, name = "SB_CTGRY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategorySequenceGen")
	@GenericGenerator(name = "subCategorySequenceGen", strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "SBCTGRY") })
	private String subCategoryId;

	@Version
	private int version;

	@Column(length = 60, name = "SB_CTGRY_NM", nullable = false)
	@NotBlank(message = "Sub category name must not be blank")
	private String subCategoryName;

	@Column(length = 100, name = "SB_CTGRY_DESC")
	private String subCategoryDescription;

	@Column(name = "SB_CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	@FutureOrPresent(message = "Sub category effective date must not be a past date")
	@JsonProperty(required = true, value = "subCategoryEffectiveDate")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeSerializer.class)
	private ZonedDateTime subCategoryEffectiveDate;

	@Column(name = "SB_CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	@JsonProperty(required = false, value = "subCategoryTerminationDate")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeSerializer.class)
	private ZonedDateTime subCategoryTerminationDate;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(subCategoryId).append(subCategoryName).append(subCategoryDescription)
				.append(subCategoryEffectiveDate).append(subCategoryTerminationDate).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SubCategoryEntity) {
			final SubCategoryEntity subCategoryEntity = (SubCategoryEntity) obj;

			boolean subCategoryEquality = new EqualsBuilder().append(this.subCategoryName.trim().toUpperCase(),
					subCategoryEntity.subCategoryName.trim().toUpperCase()).isEquals();

			return (((Objects.nonNull(this.subCategoryId) && Objects.nonNull(subCategoryId))
					&& this.subCategoryId.equals(subCategoryEntity.subCategoryId)) || subCategoryEquality);

		} else {
			return false;
		}
	}

}
