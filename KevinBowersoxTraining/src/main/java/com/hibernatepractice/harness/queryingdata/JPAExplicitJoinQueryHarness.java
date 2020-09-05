package com.hibernatepractice.harness.queryingdata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.hibernatepractice.model.FinancialAccountEntity;

public class JPAExplicitJoinQueryHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			TypedQuery<FinancialAccountEntity> accounts = entityManager
					.createQuery("select distinct a from transactions t "
							+ "join t.account a "
							+ "where t.transactionAmount> 500.00 "
							+ "and t.transactionType = 'Credit'"
							, FinancialAccountEntity.class);
			
			List<FinancialAccountEntity> resultList = accounts.getResultList();
			
			resultList.forEach(System.out::println);

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
