package com.kingshuk.specialprojects.domaindevelopment.models.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "LEARNING_RESOURCE")
public class LearningResource {

	@Id
	@Column(length = 30, name = "RES_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resourceSequenceGen")
	@GenericGenerator(name = "resourceSequenceGen", 
	strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "RSRC"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%025d") })
	private String resourceId;

	@Column(name = "RES_TITLE", length = 60, nullable = false)
	private String resourceTitle;

	@Column(name = "RES_DESC", length = 2000, nullable = false)
	private String topicDescription;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(nullable = false, name = "RES_TYPE_ID", referencedColumnName = "RES_TYPE_ID")
	private LearningResourceType resourceType;
	
	@Column(name = "RES_CONSMP_INSTRCTN", columnDefinition = "VARCHAR2(4000)", nullable = false)
	private String resourceConsumptionIntruction;
	
	@Column(name = "RES_LOC", columnDefinition = "VARCHAR2(2000)", nullable = false)
	private String resourceLocation;
	
}
