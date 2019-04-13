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
@Table(name = "RESOURCE_ACTIVITY")
public class ResourceActivity extends CommonLearningActivity {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID")
	private LearningResource learningResource;

}
