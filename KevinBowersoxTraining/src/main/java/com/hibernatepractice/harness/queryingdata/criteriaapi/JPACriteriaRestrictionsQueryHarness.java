package com.hibernatepractice.harness.queryingdata.criteriaapi;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.hibernatepractice.model.TransactionEntity;

public class JPACriteriaRestrictionsQueryHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			// Create the criteria query
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			CriteriaQuery<TransactionEntity> query = cb.createQuery(TransactionEntity.class);

			// Create the root
			Root<TransactionEntity> root = query.from(TransactionEntity.class);

			// set the path for the fields we want to use in our where clause
			Path<BigDecimal> amountPath = root.get("transactionAmount");
			Path<String> transactionType = root.get("transactionType");

			// Add the root to the query for facilitating select. This is basically
			// saying select all the fields in the transaction entity
			// Add the where clause using the paths
			query.select(root).where(cb.and(cb.lessThanOrEqualTo(amountPath, BigDecimal.valueOf(20.00)),
					cb.equal(transactionType, "Debit")));

			TypedQuery<TransactionEntity> typedQuery = entityManager.createQuery(query);

			typedQuery.getResultList().forEach(System.out::println);

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
