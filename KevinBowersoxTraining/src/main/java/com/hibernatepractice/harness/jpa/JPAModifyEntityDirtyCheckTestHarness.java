package com.hibernatepractice.harness.jpa;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernatepractice.model.KevinsBankEntity;

public class JPAModifyEntityDirtyCheckTestHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			KevinsBankEntity bankEntity = entityManager.find(KevinsBankEntity.class, 1l);
			System.out.println(entityManager.contains(bankEntity));

			if (Objects.nonNull(bankEntity)) {
				bankEntity.setName("Second National Trust");
			}

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
