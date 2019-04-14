package com.kingshuk.specialprojects.domaindevelopment.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kingshuk.specialprojects.domaindevelopment.models.learningactivity.CommonLearningActivity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper =true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRACK_LEARNING_ACTIVITY")
public class TrackLearningActivity extends CommonLearningActivity {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRK_ID", referencedColumnName = "TRK_ID")
	private LearningTrack learningTrack;

}
