package com.hibernatepractice.harness.queryingdata.criteriaapi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.hibernatepractice.model.TransactionEntity;

public class JPACriteriaAPIPagingHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();
			
			int pageNumber=2;
			int pageSize = 3;

			// Create the criteria builder
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			
			// Create the criteria query
			CriteriaQuery<TransactionEntity> query = criteriaBuilder
					.createQuery(TransactionEntity.class);

			// Create the root
			Root<TransactionEntity> root = query.from(TransactionEntity.class);

			// Add the root to the query for facilitating select. This is basically
			// saying select all the fields in the transaction entity
			query.select(root);

			TypedQuery<TransactionEntity> typedQuery = entityManager.createQuery(query);
			typedQuery.setFirstResult((pageNumber-1)*pageSize);
			typedQuery.setMaxResults(pageSize);

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
