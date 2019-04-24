package com.kingshuk.specialprojects.domaindevelopment.models.learningplan;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.kingshuk.specialprojects.domaindevelopment.models.LearningItem;
import com.kingshuk.specialprojects.domaindevelopment.models.learningactivity.LearningStatusType;
import com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEARNING_PLAN")
public class LearningPlan {

	@Id
	@Column(length = 20, name = "LRN_PLN_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topicSequenceGen")
	@GenericGenerator(name = "topicSequenceGen", strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "LRNPLN"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%014d") })
	private String learningPlanId;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name = "LRN_ITM_ID", referencedColumnName = "LRN_ITM_ID")
	private LearningItem learningItem;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LRN_PLN_ID", referencedColumnName = "LRN_PLN_ID")
	@MapKeyColumn(name = "LRN_SEQ_NUM", length = 10, nullable = false)
	private Map<Integer, LearningPlanItem> learningPlanItems;
	
	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private LearningStatusType status;

	/**
	 * A method that checks the status of the learning plan items and 
	 * returns the status accordingly
	 * TODO: Work to do here
	 * @return
	 */
	public LearningStatusType getStatus() {
		return status;
	}
	
	
	
}
