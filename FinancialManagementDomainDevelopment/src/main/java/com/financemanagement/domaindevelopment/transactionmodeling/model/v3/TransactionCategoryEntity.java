package com.financemanagement.domaindevelopment.transactionmodeling.model.v3;


import lombok.*;

import javax.persistence.*;

@Entity(name = "Category")
@Table(name = "TRANSACTION_CATEGORY_V3")
@Builder
@Data
@ToString(exclude = "parentTransaction")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategoryEntity {

    @Id
    @Column(name = "TRNSCTN_ID", nullable = false)
    private String transactionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRNSCTN_ID", nullable = false)
    @MapsId
    private TransactionMasterEntity parentTransaction;

    @Column(length = 40, name = "TRNSCTN_CTGRY", nullable = false)
    private String transactionCategory;

    @Column(length = 40, name = "TRNSCTN_SB_CTGRY")
    private String transactionSubCategory;
}
