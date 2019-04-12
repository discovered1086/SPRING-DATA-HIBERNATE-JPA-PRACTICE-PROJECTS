package com.kingshuk.specialprojects.domaindevelopment.models;

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
@Table(name = "TOPIC_TYPE")
@NamedQuery(name = "selectTopicTypeQuery", 
	query = "SELECT a FROM LearningTopicType as a")
public class LearningTopicType {

	@Id
	@Column(length = 10, name = "TPC_TYP_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trackSequenceGen")
	@GenericGenerator(name = "trackSequenceGen", strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TPCTYP"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%03d") })
	private String topicTypeId;

	@Column(name = "TPC_TYP_CD", length = 20, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private LearningTopicTypeCode topicTypeCode;

	@Column(name = "TPC_TYP_NM", length = 30, nullable = false)
	private String topicTypeName;

	@Column(name = "TPC_TYP_DESC", length = 100, nullable = false)
	private String topicTypeDesc;

}
