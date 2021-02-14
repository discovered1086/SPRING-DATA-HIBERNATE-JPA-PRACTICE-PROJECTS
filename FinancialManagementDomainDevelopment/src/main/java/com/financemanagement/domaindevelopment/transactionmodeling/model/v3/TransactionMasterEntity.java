package com.financemanagement.domaindevelopment.transactionmodeling.model.v3;

import com.financemanagement.domaindevelopment.sequencegenerators.newsequences.CommonSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.*;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "transaction")
@Table(name = "TRANSACTION_MASTER_V3")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class TransactionMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeqGen")
	@GenericGenerator(name = "transactionSeqGen", strategy = "com.financemanagement.domaindevelopment.sequencegenerators.newsequences.CommonSequenceGenerator"
			, parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TRN") })
	private String transactionId;

	@Version
	private int version;

	//@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@ManyToOne
	@JoinColumn(name = "TRNSCTN_MTHD_CD", referencedColumnName = "TRNSCTN_MTHD_CD", nullable = false)
	private TransactionMethodEntity transactionMethod;

	@Column(length = 1000, name = "TRNSCTN_DSCRPTN", nullable = false)
	private String transactionDescription;

	@OneToOne(mappedBy = "parentTransaction", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@Fetch(FetchMode.JOIN)
	private TransactionCategoryEntity transactionCategory;
	
	@OneToOne(mappedBy = "transactionMasterEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private TransactionLocationEntity transactionLocation;

	@Column(name = "TRNSCTN_NTS", columnDefinition = "VARCHAR2(4000)")
	private String transactionNotes;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.REMOVE }, fetch = FetchType.EAGER, mappedBy = "parentTransaction")
	private Set<@Valid TransactionItemEntity> itemizedTransactions;

}
