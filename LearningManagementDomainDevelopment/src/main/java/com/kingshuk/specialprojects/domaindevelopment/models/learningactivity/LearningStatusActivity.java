package com.kingshuk.specialprojects.domaindevelopment.models.learningactivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kingshuk.specialprojects.domaindevelopment.models.learningplan.LearningPlanItem;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEARNING_STATUS_ACTIVITY")
public class LearningStatusActivity extends CommonLearningActivity {
	
	@ManyToOne
	@JoinColumn(name = "LRN_ITM_ID", referencedColumnName = "LRN_ITM_ID")
	private LearningPlanItem learningPlanItem;

	@Column(name = "ACTV_TYP", length = 15)
	@Enumerated(EnumType.STRING)
	private LearningStatusType activityType;
}
