package com.hibernatepractice.harness.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernatepractice.model.KevinsBankEntity;

public class JPAReattachingEntitiesTestHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			KevinsBankEntity bankEntity = entityManager.find(KevinsBankEntity.class, 101l);

			// entityManager.clear(); //removes all entities from persistence context

			// removes a single entity from the persistence context
			entityManager.detach(bankEntity);

			System.out.println(entityManager.contains(bankEntity));
			System.out.println(bankEntity.getName());

			bankEntity.setName("Fifth National Trust");
			KevinsBankEntity bankEntity2 = entityManager.merge(bankEntity);
			System.out.println("After update" + bankEntity2.getName());

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
