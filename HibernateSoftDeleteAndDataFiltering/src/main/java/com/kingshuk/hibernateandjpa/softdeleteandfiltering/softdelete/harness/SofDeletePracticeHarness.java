package com.kingshuk.hibernateandjpa.softdeleteandfiltering.softdelete.harness;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.softdeleteandfiltering.configuration.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.softdelete.model.Account;
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.softdelete.model.AccountState;

public class SofDeletePracticeHarness {

	public static void main(String[] args) {
		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			Account account1 = Account.builder().accountNumber("30911566592").accountDesc("SBI account")
					.accountName("SBI Account").accountState(AccountState.ACTIVE).build();
			Account account2 = Account.builder().accountNumber("876465465465").accountDesc("HDFC account")
					.accountName("HDFC account").accountState(AccountState.ACTIVE).build();
			Account account3 = Account.builder().accountNumber("89789745445").accountDesc("BOA account")
					.accountName("BOA account").accountState(AccountState.ACTIVE).build();

			List<Account> accountList = new LinkedList<>();

			accountList.add(account1);
			accountList.add(account2);
			accountList.add(account3);

			accountList.forEach(session::save);

			transaction.commit();

			transaction = session.beginTransaction();

			session.remove(account2);

			transaction.commit();

			transaction = session.beginTransaction();

			TypedQuery<Account> allAccountsQuery = session.createNamedQuery("Account.FindByAccountName", Account.class);

			allAccountsQuery.setParameter("accountName", "%SBI%");

			allAccountsQuery.getResultList().forEach(System.out::println);

			System.out.println("\n*************\n Printing all active accounts\n");

			transaction.commit();

			session.close();

		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
