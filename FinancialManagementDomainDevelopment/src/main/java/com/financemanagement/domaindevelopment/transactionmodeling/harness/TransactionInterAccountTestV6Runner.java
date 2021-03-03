package com.financemanagement.domaindevelopment.transactionmodeling.harness;

import com.financemanagement.domaindevelopment.transactionmodeling.dao.TransactionTestRepository;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CountryEntity;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CurrencyEntity;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v6.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.Map;

@Component
@Profile("transaction-test-runner-v6")
public class TransactionInterAccountTestV6Runner implements CommandLineRunner {

    @Autowired
    private TransactionTestRepository repository;

    @Override
    public void run(String... args) throws Exception {
        final TransactionMasterV6Entity transactionEntity = TransactionMasterV6Entity.builder()
                .transactionDescription("Savings to DCU Account")
                .transactionNotes("Online purchase")
                .accountTransactions(new EnumMap<>(TransactionTypeEnum.class))
                .build();

        final FinancialCategoryV6Entity categoryEntity = FinancialCategoryV6Entity.builder()
                .transactionCategory("CTGRY15489")
                .transactionSubCategory("SBCTRGY165489")
                .parentTransaction(transactionEntity)
                .build();

        transactionEntity.setFinancialCategory(categoryEntity);

        final CurrencyEntity currencyEntity = repository.getEntity("USD", CurrencyEntity.class);

        final CountryEntity country = repository.getEntity("US", CountryEntity.class);

        final CurrencyEntity currencyEntity2 = repository.getEntity("INR", CurrencyEntity.class);

        final TransactionLocationV6Entity locationEntity = TransactionLocationV6Entity.builder()
                .timeZoneId("America/Chicago")
                .country(country)
                .transactionMasterEntity(transactionEntity)
                .city("Chicago")
                .region("Buffalo Grove")
                .build();

        transactionEntity.setTransactionLocation(locationEntity);

        final TransactionCategoryV6Entity methodEntity = TransactionCategoryV6Entity.builder()
                .categoryOfTransaction(TransactionCategoryTypeEnum.EXPENSE)
                .transactionCategoryDefinition("Account debit transaction")
                .transactionCategoryEffectiveDate(OffsetDateTime.now())
                .build();

        repository.saveEntity(methodEntity);

        final AccountTransactionV6Entity account = AccountTransactionV6Entity.builder()
                .transactionAmount(BigDecimal.valueOf(105.23))
                .transactionDate(OffsetDateTime.now().minusDays(5))
                .transactionAccountId("ACCT578458458")
                .transactionCurrency(currencyEntity)
                .build();

        final AccountTransactionV6Entity account2 = AccountTransactionV6Entity.builder()
                .transactionAmount(account.getTransactionAmount().multiply(BigDecimal.valueOf(72.00)))
                .transactionDate(OffsetDateTime.now())
                .transactionAccountId("ACCT57848912356")
                .transactionCurrency(currencyEntity2)
                .build();

        final Map<TransactionTypeEnum, AccountTransactionV6Entity> accountTransactions = transactionEntity.getAccountTransactions();
        accountTransactions.put(TransactionTypeEnum.DEBIT, account);
        accountTransactions.put(TransactionTypeEnum.CREDIT, account2);

        transactionEntity.setTransactionCategory(methodEntity);

        repository.saveEntity(transactionEntity);
    }

}
