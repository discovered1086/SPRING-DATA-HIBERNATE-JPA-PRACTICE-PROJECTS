package com.kingshuk.specialprojects.domaindevelopment.main.models.resource;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.kingshuk.specialprojects.domaindevelopment.main.models.topic.LearningTopicCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESOURCE_CATEGORY")
@NamedQuery(name = "selectTopicTypeQuery", 
	query = "SELECT a FROM LearningResourceType a")
public class LearningResourceCategory {

	@Id
	@Column(length = 15, name = "RES_CTGRY_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resourceTypeSequenceGen")
	@GenericGenerator(name = "resourceTypeSequenceGen", strategy = "com.kingshuk.specialprojects.domaindevelopment.main.models.sequencegenerators.CommonSequenceGenerator", parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "RESCTGRY"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%07d") })
	private String resourceCateoryId;

	@Column(name = "RES_CTGRY_NM", length = 20, nullable = false)
	private String resourceCateoryName;

	@Column(name = "RES_CTGRY_DESC", columnDefinition = "VARCHAR2(2000)", nullable = false)
	private String resourceCateoryDescription;


	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "PRNT_RES_CTGRY_ID", referencedColumnName = "RES_CTGRY_ID")
	private List<LearningResourceCategory> subCategories;
	
	@Column(name = "RES_LOC", columnDefinition = "VARCHAR2(10)", nullable = false)
	@Enumerated(EnumType.STRING)
	private LearningResourceLocation location;

}
