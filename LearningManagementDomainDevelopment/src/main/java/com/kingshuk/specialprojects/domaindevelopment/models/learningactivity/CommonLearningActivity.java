package com.kingshuk.specialprojects.domaindevelopment.models.learningactivity;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.kingshuk.specialprojects.domaindevelopment.models.LearningItem;
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
@Table(name = "ALL_ACTIVITY")
@Inheritance(strategy = InheritanceType.JOINED)
public class CommonLearningActivity {
	
	@Id
	@Column(length = 40, name = "LRNACT_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activitySeqGen")
	@GenericGenerator(name = "activitySeqGen", 
	strategy = "com.kingshuk.specialprojects.domaindevelopment.models.sequencegenerators.CommonSequenceGenerator", 
	parameters = {
			@Parameter(name = CommonSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "LRNACT"),
			@Parameter(name = CommonSequenceGenerator.NUMBER_FORMAT_PARAM, value = "%034d") })
	private String learningActivityId;
	
	@Column(name = "ACTVTY_DT", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private ZonedDateTime activityTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LRN_ITM_ID", referencedColumnName = "LRN_ITM_ID")
	private LearningItem learningItem;
	
	
	
}
