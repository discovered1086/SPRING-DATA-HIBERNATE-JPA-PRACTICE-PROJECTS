package com.hibernatepractice.util.datapopulator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernatepractice.model.CurrencyEntity;
import com.hibernatepractice.model.CurrencyKey;
import com.hibernatepractice.model.FinancialAccountEntity;
import com.hibernatepractice.model.TransactionEntity;

public class TransactionDataLoader {

	private static final String TRANSACTION_TYPE = "Debit";
	private static final String TRANSACTION_CREATED_BY = "Kingshuk Mukherjee";

	private TransactionDataLoader() {

	}

	public static void addTransactions(Session session) {
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();

			CurrencyEntity currencyEntity = session.get(CurrencyEntity.class,
					new CurrencyKey("Dollar", "United States"));

			FinancialAccountEntity account = session.get(FinancialAccountEntity.class, 52l);
			
			
			TransactionEntity transaction1 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(20.00))
					.createdBy(TRANSACTION_CREATED_BY).createdDate(LocalDateTime.now()).transactionType(TRANSACTION_TYPE)
					.transactionNotes("Shoe purchase").account(account).currency(currencyEntity).build();

			TransactionEntity transaction2 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(455.00))
					.createdBy(TRANSACTION_CREATED_BY).createdDate(LocalDateTime.now()).transactionType(TRANSACTION_TYPE)
					.transactionNotes("Laptop purchase").account(account).account(account).currency(currencyEntity)
					.build();

			TransactionEntity transaction3 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(60.00))
					.createdBy(TRANSACTION_CREATED_BY).createdDate(LocalDateTime.now()).transactionType(TRANSACTION_TYPE)
					.account(account).transactionNotes("Table lamp").account(account).currency(currencyEntity)
					.build();

			TransactionEntity transaction4 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(200.00))
					.createdBy(TRANSACTION_CREATED_BY).createdDate(LocalDateTime.now()).transactionType(TRANSACTION_TYPE)
					.account(account).transactionNotes("groceries").currency(currencyEntity).build();

			TransactionEntity transaction5 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(459.00))
					.createdBy(TRANSACTION_CREATED_BY).createdDate(LocalDateTime.now()).transactionType(TRANSACTION_TYPE)
					.transactionNotes("USCIS bill payment").account(account).currency(currencyEntity).build();

			account.setTransactions(
					Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5));

			session.save(account);

			System.out.println("Calling commit");
			transaction.commit();
		} catch (Exception e) {
			if (Objects.nonNull(transaction)) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
