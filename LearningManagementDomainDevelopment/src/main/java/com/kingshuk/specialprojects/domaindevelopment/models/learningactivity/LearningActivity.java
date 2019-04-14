package com.kingshuk.specialprojects.domaindevelopment.models.learningactivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kingshuk.specialprojects.domaindevelopment.models.resource.LearningResource;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEARNING_ACTIVITY")
public class LearningActivity extends CommonLearningActivity {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID")
	private LearningResource learningResource;

	@Column(name = "ACTV_TYP", length = 15)
	@Enumerated(EnumType.STRING)
	private LearningActivityType activityType;
}
