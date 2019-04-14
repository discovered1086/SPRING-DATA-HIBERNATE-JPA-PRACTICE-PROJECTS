package com.kingshuk.specialprojects.domaindevelopment.models.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;

import com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESOURCE_TYPE")
@NamedQuery(name = "selectTopicTypeQuery", 
	query = "SELECT a FROM LearningResourceType a")
public class LearningResourceType {

	@Id
	@Column(length = 10, name = "RES_TYPE_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resourceTypeSequenceGen")
	@GenericGenerator(name = "resourceTypeSequenceGen", strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "RESTYP"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%03d") })
	private String resourceTypeId;

	@Column(name = "RES_TYP_CD", length = 6, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private LearningResourceTypeCode resourceTypeCode;

	@Column(name = "RES_TYP_NM", length = 20, nullable = false)
	private String resourceTypeName;

	@Column(name = "RES_TYP_DESC", length = 100, nullable = false)
	private String resourceTypeDescription;
	
	private LearningResourceLocation location;

}
