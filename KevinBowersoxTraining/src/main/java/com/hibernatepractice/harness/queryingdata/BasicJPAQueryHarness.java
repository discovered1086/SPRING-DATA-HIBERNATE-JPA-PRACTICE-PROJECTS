package com.hibernatepractice.harness.queryingdata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.hibernatepractice.model.TransactionEntity;

public class BasicJPAQueryHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			Query query = entityManager.createQuery("from TransactionEntity t order by t.transactionNotes");

			@SuppressWarnings("unchecked")
			List<TransactionEntity> resultList = query.getResultList();

			resultList.forEach(System.out::println);

			TypedQuery<TransactionEntity> transactions = entityManager
					.createQuery("from TransactionEntity t order by t.transactionNotes", TransactionEntity.class);
			
			List<TransactionEntity> resultList2 = transactions.getResultList();
			
			resultList2.forEach(System.out::println);

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
