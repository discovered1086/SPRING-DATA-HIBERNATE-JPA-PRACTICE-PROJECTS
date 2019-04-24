package com.kingshuk.hibernateandjpa.exploringmapkeycolumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "LEARNING_PLAN_ITEM")
public class LearningPlanItem {

	@Id
	@Column(length = 20, name = "LRN_PLN_ITM_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "learningPlanItemSeq")
	@SequenceGenerator(name = "learningPlanItemSeq", sequenceName = "LEARNING_PLAN_ITEM_SEQ", allocationSize = 1)
	private Long planItemId;
	
	/*@Column(name = "LRN_SEQ", length = 10, nullable = false)
	private Integer learningSequenceNum;*/

	@Column(name = "LRN_INSTRCTNS", columnDefinition = "VARCHAR2(4000)")
	private String learningInstructions;
}
