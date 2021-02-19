package com.financemanagement.domaindevelopment.transactionmodeling.harness;

import com.financemanagement.domaindevelopment.transactionmodeling.dao.TransactionTestRepository;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CountryEntity;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CurrencyEntity;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v5.*;
import com.financemanagement.domaindevelopment.v2.model.enums.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Profile("transaction-test-runner-v5")
public class TransactionInterAccountTestV5Runner implements CommandLineRunner {

    @Autowired
    private TransactionTestRepository repository;

    @Override
    public void run(String... args) throws Exception {
        final TransactionMasterV5Entity transactionEntity = TransactionMasterV5Entity.builder()
                .transactionDescription("Savings to DCU Account")
                .transactionNotes("Online purchase")
                .accountTransactions(new HashMap<>())
                .build();

        final TransactionCategoryV5Entity categoryEntity = TransactionCategoryV5Entity.builder()
                .transactionCategory("CTGRY15489")
                .transactionSubCategory("SBCTRGY165489")
                .parentTransaction(transactionEntity)
                .build();

        transactionEntity.setTransactionCategory(categoryEntity);

        final CurrencyEntity currencyEntity = repository.getEntity("USD", CurrencyEntity.class);

        final CountryEntity country = repository.getEntity("US", CountryEntity.class);

        final CurrencyEntity currencyEntity2 = repository.getEntity("INR", CurrencyEntity.class);

        final TransactionLocationV5Entity locationEntity = TransactionLocationV5Entity.builder()
                .timeZoneId("America/Chicago")
                .country(country)
                .transactionMasterEntity(transactionEntity)
                .city("Chicago")
                .region("Buffalo Grove")
                .build();

        transactionEntity.setTransactionLocation(locationEntity);

        final TransactionTypeV5Entity methodEntity = TransactionTypeV5Entity.builder()
                .transactionMethodCode("ACCT_DEBIT")
                .typeOfTransaction(TransactionTypeEnum.EXPENSE)
                .transactionMethodDefinition("Account debit transaction")
                .transactionMethodEffectiveDate(OffsetDateTime.now())
                .build();

        final TransactionTypeV5Entity methodEntity2 = TransactionTypeV5Entity.builder()
                .transactionMethodCode("ACCT_CREDIT")
                .typeOfTransaction(TransactionTypeEnum.INCOME)
                .transactionMethodDefinition("Account credit transaction")
                .transactionMethodEffectiveDate(OffsetDateTime.now())
                .build();

        repository.saveEntity(methodEntity2);
        repository.saveEntity(methodEntity);

        final AccountTransactionV5Entity account = AccountTransactionV5Entity.builder()
                .transactionAmount(BigDecimal.valueOf(105.23))
                .transactionDate(OffsetDateTime.now().minusDays(5))
                .transactionMethod(methodEntity)
                .transactionAccountId("ACCT578458458")
                .transactionCurrency(currencyEntity)
                .build();

        final AccountTransactionV5Entity account2 = AccountTransactionV5Entity.builder()
                .transactionAmount(account.getTransactionAmount().multiply(BigDecimal.valueOf(72.00)))
                .transactionDate(OffsetDateTime.now())
                .transactionMethod(methodEntity2)
                .transactionAccountId("ACCT57848912356")
                .transactionCurrency(currencyEntity2)
                .build();

        final Map<TransactionTypeV5Entity, AccountTransactionV5Entity> accountTransactions = transactionEntity.getAccountTransactions();
        accountTransactions.put(methodEntity, account);
        accountTransactions.put(methodEntity2, account2);

        repository.saveEntity(transactionEntity);
    }

}
