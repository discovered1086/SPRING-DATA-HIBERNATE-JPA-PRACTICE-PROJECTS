package com.financemanagement.domaindevelopment.transactionmodeling.model.v6;


import com.financemanagement.domaindevelopment.sequencegenerators.newsequences.CommonSequenceGenerator;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CurrencyEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "transactionItem")
@Table(name = "TRANSACTION_ITEM_V6")
@NoArgsConstructor
@Builder
@Data
@ToString(exclude = "parentTransaction")
@AllArgsConstructor
public class TransactionItemV6Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ITM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemizedTransactionSeqGen")
	@GenericGenerator(name = "itemizedTransactionSeqGen", strategy = "com.financemanagement.domaindevelopment.sequencegenerators.newsequences.CommonSequenceGenerator", parameters = {
			@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "ITMTRN") })
	private String transactionItemId;

	@Version
	private int version;

	@Column(length = 1000, name = "TRNSCTN_ITM_DSCRPTN", nullable = false)
	private String transactionItemDescription;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_ITM_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	private CurrencyEntity transactionItemCurrency;

	@Column(name = "TRNSCTN_ITM_AMT", columnDefinition = "NUMBER(20,2)")
	private BigDecimal transactionItemAmount;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PRNT_TRNSCTN_ID", referencedColumnName = "TRNSCTN_ID", nullable = false, updatable = false)
	private TransactionMasterV6Entity parentTransaction;

}
