package com.kingshuk.specialprojects.domaindevelopment.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TOPIC_LEARNING_ACTIVITY")
public class TopicLearningActivity extends CommonLearningActivity {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TPC_ID", referencedColumnName = "TPC_ID")
	private LearningTopic learningTopic;

}
