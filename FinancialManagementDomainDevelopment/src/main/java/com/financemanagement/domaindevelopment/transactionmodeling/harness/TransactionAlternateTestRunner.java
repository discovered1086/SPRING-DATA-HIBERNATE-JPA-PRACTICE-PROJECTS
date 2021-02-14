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
import java.util.Collections;

@Component
@Profile("transaction-test-runner-v2")
public class TransactionAlternateTestRunner implements CommandLineRunner {

	@Autowired
	private TransactionTestRepository repository;

	@Override
	public void run(String... args) throws Exception {
		final TransactionMasterAlternateEntity transactionEntity = TransactionMasterAlternateEntity.builder()
				.transactionDescription("Purchasing wallet from ebay")
				.transactionNotes("Online purchase")
				.build();

		final TransactionCategoryAlternateEntity categoryEntity = TransactionCategoryAlternateEntity.builder()
				.transactionCategory("CTGRY15465")
				.transactionSubCategory("SBCTRGY1654654")
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

		final TransactionLocationAlternateEntity locationEntity = TransactionLocationAlternateEntity.builder()
				.timeZoneId("America/Chicago")
				.country(country)
				.transactionMasterEntity(transactionEntity)
				.city("Chicago")
				.region("Buffalo Grove")
				.build();

		transactionEntity.setTransactionLocation(locationEntity);

		final TransactionTypeAlternateEntity methodEntity = TransactionTypeAlternateEntity.builder()
				.transactionMethodCode("CRD_TRN")
				.typeOfTransaction(TransactionTypeEnum.EXPENSE)
				.transactionMethodDefinition("Credit Card Transaction")
				.transactionMethodEffectiveDate(OffsetDateTime.now())
				.build();

		repository.saveEntity(methodEntity);

		final AccountTransactionAlternateEntity account = AccountTransactionAlternateEntity.builder()
				.transactionAmount(BigDecimal.valueOf(105.23))
				.transactionDate(OffsetDateTime.now())
				.transactionMethod(methodEntity)
				.transactionAccountId("ACCT578458458")
				.transactionMasterEntity(transactionEntity)
				.transactionCurrency(currencyEntity)
				.build();

		transactionEntity.setAccountTransactions(Collections.singleton(account));

		repository.saveEntity(transactionEntity);
	}

}
