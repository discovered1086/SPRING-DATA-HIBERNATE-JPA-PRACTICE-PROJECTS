package com.kingshuk.specialprojects.domaindevelopment.models.learningactivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kingshuk.specialprojects.domaindevelopment.models.topic.LearningTopic;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEARNING_CONTENT_MANGEMENT_ACTIVITY")
public class LearningContentManagementActivity extends CommonLearningActivity {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TPC_ID", referencedColumnName = "TPC_ID")
	private LearningTopic learningTopic;
	
	@Column(name = "ACTV_TYP", length = 15)
	@Enumerated(EnumType.STRING)
	private LearningContentManagementActivityType activityType;

}
