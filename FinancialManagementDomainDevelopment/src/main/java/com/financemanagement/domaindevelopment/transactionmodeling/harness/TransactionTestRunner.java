package com.financemanagement.domaindevelopment.transactionmodeling.harness;

import com.financemanagement.domaindevelopment.transactionmodeling.dao.TransactionTestRepository;
import com.financemanagement.domaindevelopment.transactionmodeling.model.*;
import com.financemanagement.domaindevelopment.v2.model.enums.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Profile("transaction-test-runner")
public class TransactionTestRunner implements CommandLineRunner {

	@Autowired
	private TransactionTestRepository repository;

	@Override
	public void run(String... args) throws Exception {
		final TransactionMasterEntity transactionEntity = TransactionMasterEntity.builder()
				.transactionDescription("Purchasing wallet from ebay")
				.transactionNotes("Online purchase")
				.build();

		final TransactionCategoryEntity categoryEntity = TransactionCategoryEntity.builder()
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

		final TransactionLocationEntity locationEntity = TransactionLocationEntity.builder()
				.timeZoneId("America/Chicago")
				.country(country)
				.city("Chicago")
				.region("Buffalo Grove")
				.build();

		transactionEntity.setTransactionLocation(locationEntity);

		repository.saveEntity(currencyEntity);

		final TransactionMethodEntity methodEntity = TransactionMethodEntity.builder()
				.transactionMethodCode("CRD_TRN")
				.typeOfTransaction(TransactionTypeEnum.EXPENSE)
				.transactionMethodDefinition("Credit Card Transaction")
				.transactionMethodEffectiveDate(OffsetDateTime.now())
				.build();

		repository.saveEntity(methodEntity);

		transactionEntity.setTransactionMethod(methodEntity);

		Map<AccountTransactionType, AccountTransactionHelperEntity> accountTransactionMap = new HashMap<>();
		accountTransactionMap.put(AccountTransactionType.SOURCE_ACCOUNT, AccountTransactionHelperEntity.builder()
				.transactionAmount(BigDecimal.valueOf(105.23))
				.transactionDate(OffsetDateTime.now())
				.transactionAccountId("ACCT578458458")
				.transactionCurrency(currencyEntity)
				.build());

		final AccountTransactionEntity accountTransactionEntity = AccountTransactionEntity.builder()
				.transactionMasterEntity(transactionEntity)
				.accountTransactions(accountTransactionMap)
				.build();

		repository.saveEntity(accountTransactionEntity);
	}

}
