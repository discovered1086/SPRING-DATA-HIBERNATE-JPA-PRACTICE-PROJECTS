package com.financemanagement.domaindevelopment.transactionmodeling.harness;

import com.financemanagement.domaindevelopment.transactionmodeling.dao.TransactionTestRepository;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CountryEntity;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v3.CurrencyEntity;
import com.financemanagement.domaindevelopment.transactionmodeling.model.v4.*;
import com.financemanagement.domaindevelopment.v2.model.enums.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
@Profile("transaction-test-runner-v3")
public class TransactionInterAccountTestRunner implements CommandLineRunner {

    @Autowired
    private TransactionTestRepository repository;

    @Override
    public void run(String... args) throws Exception {
        final TransactionMasterAlternateEntity transactionEntity = TransactionMasterAlternateEntity.builder()
                .transactionDescription("Savings to DCU Account")
                .transactionNotes("Online purchase")
                .build();

        final TransactionCategoryAlternateEntity categoryEntity = TransactionCategoryAlternateEntity.builder()
                .transactionCategory("CTGRY15489")
                .transactionSubCategory("SBCTRGY165489")
                .parentTransaction(transactionEntity)
                .build();

        transactionEntity.setTransactionCategory(categoryEntity);

		final CurrencyEntity currencyEntity = CurrencyEntity.builder()
				.currencyCode("USD")
				.description("United States Dollars")
				.numericCode("840")
				.build();

		final CountryEntity country = CountryEntity.builder()
				.countryCode("US")
				.countryName("United States of America")
				.isoAlpha3("ABC")
				.geoNameId("JDS")
				.currencies(Collections.singleton(currencyEntity))
				.build();

		currencyEntity.setCountry(country);

		repository.saveEntity(currencyEntity);

		final CurrencyEntity currencyEntity2 = CurrencyEntity.builder()
				.currencyCode("INR")
				.description("Indian Rupees")
				.numericCode("316")
				.build();

		final CountryEntity country2 = CountryEntity.builder()
				.countryCode("IN")
				.countryName("Republic of India")
				.isoAlpha3("ABC")
				.geoNameId("JDS")
				.currencies(Collections.singleton(currencyEntity))
				.build();

		currencyEntity2.setCountry(country2);

		repository.saveEntity(currencyEntity2);

        final TransactionLocationAlternateEntity locationEntity = TransactionLocationAlternateEntity.builder()
                .timeZoneId("America/Chicago")
                .country(country)
                .transactionMasterEntity(transactionEntity)
                .city("Chicago")
                .region("Buffalo Grove")
                .build();

        transactionEntity.setTransactionLocation(locationEntity);

        final TransactionTypeAlternateEntity methodEntity = TransactionTypeAlternateEntity.builder()
				.transactionMethodCode("ACCT_DEBIT")
				.typeOfTransaction(TransactionTypeEnum.EXPENSE)
				.transactionMethodDefinition("Account debit transaction")
				.transactionMethodEffectiveDate(OffsetDateTime.now())
				.build();

        final TransactionTypeAlternateEntity methodEntity2 = TransactionTypeAlternateEntity.builder()
                .transactionMethodCode("ACCT_CREDIT")
                .typeOfTransaction(TransactionTypeEnum.INCOME)
                .transactionMethodDefinition("Account credit transaction")
                .transactionMethodEffectiveDate(OffsetDateTime.now())
                .build();

        repository.saveEntity(methodEntity2);
		repository.saveEntity(methodEntity);

        final AccountTransactionAlternateEntity account = AccountTransactionAlternateEntity.builder()
                .transactionAmount(BigDecimal.valueOf(105.23))
                .transactionDate(OffsetDateTime.now().minusDays(5))
                .transactionMethod(methodEntity)
                .transactionAccountId("ACCT578458458")
                .transactionMasterEntity(transactionEntity)
                .transactionCurrency(currencyEntity)
                .build();

        final AccountTransactionAlternateEntity account2 = AccountTransactionAlternateEntity.builder()
                .transactionAmount(BigDecimal.valueOf(7589))
                .transactionDate(OffsetDateTime.now())
                .transactionMethod(methodEntity2)
                .transactionAccountId("ACCT57848912356")
                .transactionMasterEntity(transactionEntity)
                .transactionCurrency(currencyEntity2)
                .build();

        transactionEntity.setAccountTransactions(new HashSet<>(Arrays.asList(account2, account)));

        repository.saveEntity(transactionEntity);
    }

}
