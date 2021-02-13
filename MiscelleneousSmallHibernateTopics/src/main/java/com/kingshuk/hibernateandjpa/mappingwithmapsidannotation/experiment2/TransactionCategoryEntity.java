package com.kingshuk.hibernateandjpa.mappingwithmapsidannotation.experiment2;


import com.openpojo.business.annotation.BusinessKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Category")
@Table(name = "TRANSACTION_CATEGORY_MAPSID")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategoryEntity implements Serializable {

    @Id
    @Column(name = "TRANSACTION_ID", nullable = false)
    private long transactionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRNSCTN_ID", nullable = false)
    @MapsId
    private TransactionEntity parentTransaction;

    @BusinessKey
    @Column(length = 40, name = "TRNSCTN_CTGRY", nullable = false)
    private String transactionCategory;

    @BusinessKey(required = false)
    @Column(length = 40, name = "TRNSCTN_SB_CTGRY")
    private String transactionSubCategory;
}
