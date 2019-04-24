package com.kingshuk.hibernateandjpa.exploringmapkeycolumn;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEARNING_PLAN")
public class LearningPlan {

	@Id
	@Column(length = 20, name = "LRN_PLN_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "learningPlanSeq")
	@SequenceGenerator(name = "learningPlanSeq", sequenceName = "LEARNING_PLAN_SEQ", allocationSize = 1)
	private Long learningPlanId;
	
		
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LRN_PLN_ID", referencedColumnName = "LRN_PLN_ID", nullable = true)
	@MapKeyColumn(name = "LRN_SEQ_NUM", length = 10, nullable = true)
	private Map<Integer, LearningPlanItem> learningPlanItems;
	
}
