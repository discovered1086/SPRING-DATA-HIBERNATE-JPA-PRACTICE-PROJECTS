package com.kingshuk.specialprojects.domaindevelopment.models.topic;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.kingshuk.specialprojects.domaindevelopment.models.resource.LearningResource;
import com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEARNING_TOPIC")
public class LearningTopic{

	@Id
	@Column(length = 20, name = "TPC_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topicSequenceGen")
	@GenericGenerator(name = "topicSequenceGen", 
	strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TPC"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%015d") })
	private String topicId;

	@Column(name = "TPC_TTLE", length = 60, nullable = false)
	private String topicTitle;

	@Column(name = "TPC_DESC", length = 2000, nullable = false)
	private String topicDescription;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(nullable = false, name = "TPC_TYP_ID", referencedColumnName = "TPC_TYP_ID")
	private LearningTopicCategory topicType;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(joinColumns = @JoinColumn(name = "TPC_ID", referencedColumnName = "TPC_ID"),
			   inverseJoinColumns = @JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID"))
	private List<LearningResource> learningResources;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "PRNT_LRNITM_ID", referencedColumnName = "PRNT_LRNITM_ID")
	private List<LearningTopic> subTopics;
}
