package com.kingshuk.specialprojects.domaindevelopment.main.models.topic;

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
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;

import com.kingshuk.specialprojects.domaindevelopment.main.models.sequencegenerators.CommonSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TOPIC_CATEGORY")
@NamedQuery(name = "selectTopicTypeQuery", 
	query = "SELECT a FROM LearningTopicCategory as a")
public class LearningTopicCategory {

	@Id
	@Column(length = 17, name = "TPC_CTGRY_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topicCategorySeq")
	@GenericGenerator(name = "topicCategorySeq", strategy = "com.kingshuk.specialprojects.domaindevelopment.main.models.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TPCCTGRY"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%010d") })
	private String topicCategoryId;

	@Column(name = "TPC_CAT_NM", length = 50, nullable = false)
	private String topicCategoryName;

	@Column(name = "TPC_CAT_DESC", columnDefinition = "VARCHAR2(2000)", nullable = false)
	private String topicCategoryDescription;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "PRNT_TPC_CTGRY_ID", referencedColumnName = "TPC_CTGRY_ID")
	private List<LearningTopicCategory> subCategories;
}
