package com.hibernatepractice.harness.advancedmapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.AccountType;
import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.model.FinancialAccountEntity;

public class EnumerationMappingTestHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			BankEntity bank = session.get(BankEntity.class, 1l);

			FinancialAccountEntity account = FinancialAccountEntity.builder().accountName("Santander Account")
					.accountTypeId("3").bank(bank).accountType(AccountType.MONEY_MARKET).createdBy("Kingshuk Mukherjee")
					.createdDate(LocalDateTime.now()).currentBalance(BigDecimal.valueOf(10000.00))
					.openedDate(LocalDateTime.now()).build();

			System.out.println("Before invoking save method......\n");
			System.out.println(session.contains(account));
			System.out.println("===============================================\n");

			session.save(account);

			System.out.println("After invoking save method......\n");
			System.out.println(session.contains(account));
			System.out.println("===============================================\n");

			session.getTransaction().commit();

			Session session2 = HibernateConfigUtil.getSessionFactory().openSession();

			session2.beginTransaction();

			FinancialAccountEntity dbAccount = session2.get(FinancialAccountEntity.class, 52l);

			System.out.println(dbAccount);

			session2.getTransaction().commit();

			session2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
