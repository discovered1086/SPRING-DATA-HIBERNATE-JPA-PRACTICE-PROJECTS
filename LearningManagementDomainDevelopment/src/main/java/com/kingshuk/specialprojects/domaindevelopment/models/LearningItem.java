package com.kingshuk.specialprojects.domaindevelopment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LearningItem {

	@Id
	@Column(length = 20, name = "LRN_ITM_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topicSequenceGen")
	@GenericGenerator(name = "topicSequenceGen", 
	strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TPC"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%015d") })
	private String topicId;

	@Column(name = "LRN_ITM_TTLE", length = 60, nullable = false)
	private String topicTitle;

	@Column(name = "LRN_ITM_DESC", length = 2000, nullable = false)
	private String topicDescription;
}
