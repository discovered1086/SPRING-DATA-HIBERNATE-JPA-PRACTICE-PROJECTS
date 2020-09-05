package com.hibernatepractice.harness.queryingdata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAQueryUsingFunctionsHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			Query accounts = entityManager
					.createQuery("select a.accountName, "
							+ "a.bank.bankName "
							+ "from transactions t "
							+ "join t.account a "
							+ "join a.bank b "
							+ "where t.transactionAmount> 500.00 "
							+ "and t.transactionType = 'Credit' ");
			
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = accounts.getResultList();
			
			resultList.forEach(row -> {
				System.out.println("Account Name: " +row [0]+ ", bank name: "+ row[1]);
			});

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}

			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}

		}

	}

}
