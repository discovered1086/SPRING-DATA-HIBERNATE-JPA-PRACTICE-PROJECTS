package com.financemanagement.domaindevelopment.v2.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.financemanagement.domaindevelopment.deserialization.DateTimeDeSerializer;
import com.financemanagement.domaindevelopment.deserialization.DateTimeSerializer;
import com.financemanagement.domaindevelopment.v2.model.enums.TransactionTypeEnum;
import com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FINANCE_CATEGORY")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 15, name = "CTGRY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequenceGen")
	@GenericGenerator(name = "categorySequenceGen", strategy = "com.financemanagement.domaindevelopment.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "CTGRY") })
	private String categoryId;

	@Version
	private int version;

	@Column(length = 60, name = "CTGRY_NM", nullable = false)
	@NotBlank(message = "Category name must not be blank")
	private String categoryName;

	@Column(length = 100, name = "CTGRY_DESC")
	private String categoryDescription;

	@Column(name = "CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	@FutureOrPresent(message = "Category effective date must not be a past date")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeSerializer.class)
	private ZonedDateTime categoryEffectiveDate;

	@Column(name = "CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeSerializer.class)
	@JsonProperty(required = false, value = "categoryTerminationDate")
	private ZonedDateTime categoryTerminationDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "CTGRY_TRN_TYP")
	@NotNull(message = "Transaction type must not be empty")
	@JsonProperty(required = false, value = "transactionType")
	private TransactionTypeEnum categoryTransactionType;

	@ManyToMany
	@JoinTable(name = "FINANCE_CTGRY_SBCTGRY_MAPPING",
	joinColumns = @JoinColumn(name = "CTRGY_ID", referencedColumnName = "CTGRY_ID"), inverseJoinColumns = @JoinColumn(name = "SB_CTGRY_ID", referencedColumnName = "SB_CTGRY_ID"))
	private Set<SubCategoryEntity> subCategoryEntities;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(categoryId).append(categoryName).append(categoryDescription)
				.append(categoryEffectiveDate).append(categoryTerminationDate).append(categoryTransactionType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CategoryEntity) {
			final CategoryEntity categoryEntity = (CategoryEntity) obj;

			boolean categoryEquality = new EqualsBuilder()
					.append(this.categoryName.trim().toUpperCase(), categoryEntity.categoryName.trim().toUpperCase())
					.isEquals() && Objects.deepEquals(this.subCategoryEntities, categoryEntity.subCategoryEntities);

			return (((Objects.nonNull(this.categoryId) && Objects.nonNull(categoryId))
					&& this.categoryId.equals(categoryEntity.categoryId)) || categoryEquality);

		} else {
			return false;
		}
	}

}
