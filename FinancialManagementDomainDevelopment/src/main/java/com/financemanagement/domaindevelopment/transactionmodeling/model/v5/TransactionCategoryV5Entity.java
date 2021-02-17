package com.financemanagement.domaindevelopment.transactionmodeling.model.v5;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Category")
@Table(name = "TRANSACTION_CATEGORY_V5")
@Builder
@Data
@ToString(exclude = "parentTransaction")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategoryV5Entity implements Serializable {

    @Id
    @Column(name = "TRNSCTN_ID", nullable = false)
    private String transactionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRNSCTN_ID", nullable = false)
    @MapsId
    private TransactionMasterV5Entity parentTransaction;

    @Column(length = 40, name = "TRNSCTN_CTGRY", nullable = false)
    private String transactionCategory;

    @Column(length = 40, name = "TRNSCTN_SB_CTGRY")
    private String transactionSubCategory;
}
