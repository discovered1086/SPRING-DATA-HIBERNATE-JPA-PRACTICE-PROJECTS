package com.hibernatepractice.harness.queryingdata;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernatepractice.model.TransactionEntity;

@SuppressWarnings("squid:CommentedOutCodeLine")
public class JPAQueryUsingOperatorsHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

//			Query query = entityManager.createQuery("from TransactionEntity t "
//					+ "where t.amount between "
//					+ "order by t.transactionNotes");
//
//			@SuppressWarnings("unchecked")
//			List<TransactionEntity> resultList = query.getResultList();
//
//			resultList.forEach(System.out::println);

//			TypedQuery<TransactionEntity> transactions = entityManager
//					.createQuery("from TransactionEntity t "
//							+ "where (t.transactionAmount between 25 and 100)"
//							+ "and t.transactionNotes like '%purchase%'"
//							+ "order by t.transactionNotes", TransactionEntity.class);
//			
//			List<TransactionEntity> resultList2 = transactions.getResultList();
			
//			TypedQuery<TransactionEntity> transactions = entityManager
//					.createQuery("from TransactionEntity t "
//							+ "where (t.transactionAmount between :lowerLimit and :upperLimit)"
//							+ "and t.transactionNotes like '%purchase%'"
//							+ "order by t.transactionNotes", TransactionEntity.class);
//			
//			transactions.setParameter("lowerLimit", BigDecimal.valueOf(25.00))
//						.setParameter("upperLimit", BigDecimal.valueOf(100.00));
//			
//			
//			List<TransactionEntity> resultList2 = transactions.getResultList();
			
			List<TransactionEntity> resultList2 = entityManager
					.createQuery("from TransactionEntity t "
							+ "where (t.transactionAmount between :lowerLimit and :upperLimit)"
							+ "and t.transactionNotes like '%purchase%'"
							+ "order by t.transactionNotes", TransactionEntity.class)
					.setParameter("lowerLimit", BigDecimal.valueOf(25.00))
					.setParameter("upperLimit", BigDecimal.valueOf(100.00))
					.getResultList();
						
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
