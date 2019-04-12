package com.kingshuk.hibernate.selfjoiningrelations.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.kingshuk.hibernate.selfjoiningrelations.entities.sequence.SequenceGeneratorClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "LEARNING_TRACK")
public class LearningTrack {

	@Id
	@Column(length = 20, name = "TRACK_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trackSequenceGen")
	@GenericGenerator(name = "trackSequenceGen",
		strategy = "com.kingshuk.hibernate.selfjoiningrelations.entities.sequence.SequenceGeneratorClass",
		parameters = {
				@Parameter(name = SequenceGeneratorClass.INCREMENT_PARAM, value="25"),
				@Parameter(name = SequenceGeneratorClass.VALUE_PREFIX_PARAM, value="TRK"),
				@Parameter(name = SequenceGeneratorClass.NUMBER_FORMAT_PARAM, value="%010d")
		}	)
	private String trackId;
	
	@Column(name = "TRACK_NAME", length = 30, nullable = false)
	private String trackName;
	
	@Column(name = "TRACK_DESC", length = 200, nullable = false)
	private String trackDescription;
	
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "PARENT_TRACK_ID", referencedColumnName = "TRACK_ID")
	private List<LearningTrack> subTracks;
}
