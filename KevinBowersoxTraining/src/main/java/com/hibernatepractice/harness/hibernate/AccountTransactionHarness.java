package com.hibernatepractice.harness.hibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.model.FinancialAccountEntity;
import com.hibernatepractice.model.TransactionEntity;
import com.hibernatepractice.util.EntityCreationUtil;

public class AccountTransactionHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			BankEntity bank = EntityCreationUtil.createBankEntity("KevinsBankEntity of America");

			session.save(bank);

			List<TransactionEntity> transactions = EntityCreationUtil.createTransactions();

			TransactionEntity transaction1 = transactions.get(0);
			TransactionEntity transaction2 = transactions.get(1);

			FinancialAccountEntity account = FinancialAccountEntity.builder().accountName("BOA Account")
					.accountTypeId("3").bankId(bank).createdBy("Kingshuk Mukherjee").createdDate(LocalDateTime.now())
					.currentBalance(BigDecimal.valueOf(10000.00)).openedDate(LocalDateTime.now())
					.transactions(Arrays.asList(transaction1, transaction2)).build();

			transaction1.setAccount(account);
			transaction2.setAccount(account);

			session.save(account);

			session.getTransaction().commit();

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
