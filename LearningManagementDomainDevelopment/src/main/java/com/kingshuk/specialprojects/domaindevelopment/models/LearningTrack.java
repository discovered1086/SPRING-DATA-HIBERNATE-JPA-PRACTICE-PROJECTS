package com.kingshuk.specialprojects.domaindevelopment.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "LEARNING_TRACK")
public class LearningTrack {

	@Id
	@Column(length = 20, name = "TRACK_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trackSequenceGen")
	@GenericGenerator(name = "trackSequenceGen",
		strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator",
		parameters = {
				@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value="1"),
				@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value="TRK"),
				@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value="%015d")
		}	)
	private String trackId;
	
	@Column(name = "TRACK_TITLE", length = 30, nullable = false)
	private String trackTitle;
	
	@Column(name = "TRACK_DESC", length = 1000, nullable = false)
	private String trackDescription;
	
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "PARENT_TRACK_ID", referencedColumnName = "TRACK_ID")
	private List<LearningTrack> subTracks;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(joinColumns = @JoinColumn(name = "TRACK_ID", referencedColumnName = "TRACK_ID"),
			   inverseJoinColumns = @JoinColumn(name = "TOPIC_ID", referencedColumnName = "TOPIC_ID"))
	private List<LearningTopic> learningTopics;
}
