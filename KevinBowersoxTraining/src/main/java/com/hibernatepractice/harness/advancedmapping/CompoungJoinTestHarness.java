package com.hibernatepractice.harness.advancedmapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.model.CurrencyEntity;
import com.hibernatepractice.model.CurrencyKey;
import com.hibernatepractice.model.FinancialAccountEntity;
import com.hibernatepractice.model.TransactionEntity;

public class CompoungJoinTestHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			BankEntity bank = session.get(BankEntity.class, 1l);

			CurrencyEntity currencyEntity = session.get(CurrencyEntity.class,
					new CurrencyKey("Dollar", "United States"));

			TransactionEntity transaction1 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(20.00))
					.createdBy("Kingshuk Mukherjee").createdDate(LocalDateTime.now()).transactionType("Debit")
					.transactionNotes("Shoe purchase").currency(currencyEntity).build();

			FinancialAccountEntity account = FinancialAccountEntity.builder().accountName("BOA Account")
					.accountTypeId("3").bank(bank).createdBy("Kingshuk Mukherjee").createdDate(LocalDateTime.now())
					.currentBalance(BigDecimal.valueOf(10000.00)).openedDate(LocalDateTime.now())
					.transactions(Arrays.asList(transaction1)).build();

			transaction1.setAccount(account);

			System.out.println("Before invoking save method......\n");
			System.out.println(session.contains(transaction1));

			System.out.println(session.contains(account));
			System.out.println("===============================================\n");

			session.save(account);

			System.out.println("After invoking save method......\n");
			System.out.println(session.contains(transaction1));

			System.out.println(session.contains(account));
			System.out.println("===============================================\n");

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
