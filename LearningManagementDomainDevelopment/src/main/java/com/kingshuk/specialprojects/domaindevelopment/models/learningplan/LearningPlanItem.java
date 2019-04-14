package com.kingshuk.specialprojects.domaindevelopment.models.learningplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.kingshuk.specialprojects.domaindevelopment.models.LearningItem;
import com.kingshuk.specialprojects.domaindevelopment.models.LearningItem.LearningItemBuilder;
import com.kingshuk.specialprojects.domaindevelopment.models.resource.LearningResource;
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
@Table(name = "LEARNING_PLAN_ITEM")
public class LearningPlanItem {

	@Id
	@Column(length = 30, name = "LRN_PLN_ITM_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topicSequenceGen")
	@GenericGenerator(name = "topicSequenceGen", strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "LRNPLNITM"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%020d") })
	private String planItemId;

	@OneToMany
	@JoinColumn(nullable = false, name = "LRN_RES_ID", referencedColumnName = "RES_ID")
	private LearningResource learningResource;
	
	@Column(name = "LRN_INSTRCTNS", columnDefinition = "VARCHAR2(4000)")
	private String learningInstructions;
}
