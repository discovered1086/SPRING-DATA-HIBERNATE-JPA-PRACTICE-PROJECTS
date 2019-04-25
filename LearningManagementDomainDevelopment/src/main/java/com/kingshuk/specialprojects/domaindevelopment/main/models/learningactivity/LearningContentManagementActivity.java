package com.kingshuk.specialprojects.domaindevelopment.main.models.learningactivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEARNING_CONTENT_MANGEMENT_ACTIVITY")
public class LearningContentManagementActivity extends CommonLearningActivity {
	
	@Column(name = "ACTV_TYP", length = 15)
	@Enumerated(EnumType.STRING)
	private LearningContentManagementActivityType activityType;

}
