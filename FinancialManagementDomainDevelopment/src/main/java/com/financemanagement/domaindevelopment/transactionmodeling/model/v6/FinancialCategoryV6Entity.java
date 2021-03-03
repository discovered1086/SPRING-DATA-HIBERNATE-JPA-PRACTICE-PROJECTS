package com.financemanagement.domaindevelopment.transactionmodeling.model.v6;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FINANCIAL_CATEGORY_V6")
@Builder
@Data
@ToString(exclude = "parentTransaction")
@NoArgsConstructor
@AllArgsConstructor
public class FinancialCategoryV6Entity implements Serializable {

    @Id
    @Column(name = "TRNSCTN_ID", nullable = false)
    private String transactionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRNSCTN_ID", nullable = false)
    @MapsId
    private TransactionMasterV6Entity parentTransaction;

    @Column(length = 40, name = "FNNCL_CTGRY", nullable = false)
    private String transactionCategory;

    @Column(length = 40, name = "FNNCL_SB_CTGRY")
    private String transactionSubCategory;
}
