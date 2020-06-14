package com.hibernatepractice.harness.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.util.EntityCreationUtil;

public class JPASimpleConfigurationTestHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			BankEntity bankEntity = EntityCreationUtil.createBankEntity("Wells Fargo");

			entityManager.persist(bankEntity);

			transaction.commit();

		} catch (Exception e) {
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
