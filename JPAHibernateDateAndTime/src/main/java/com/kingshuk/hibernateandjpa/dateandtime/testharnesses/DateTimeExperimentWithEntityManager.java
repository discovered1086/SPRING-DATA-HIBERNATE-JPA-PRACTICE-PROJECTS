package com.kingshuk.hibernateandjpa.dateandtime.testharnesses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kingshuk.hibernateandjpa.dateandtime.config.JpaEntityManagerFactory;
import com.kingshuk.hibernateandjpa.dateandtime.model.SupportedDateTimeEntity;

public class DateTimeExperimentWithEntityManager {

	public static void main(String[] args) {
		EntityManager entityManager = getJpaEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();

		SupportedDateTimeEntity dateTimeEntity = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
				.localTime(LocalTime.now()).localDateTime(LocalDateTime.now()).offsetDateTime(OffsetDateTime.now())
				.offsetTime(OffsetTime.now()).zonedDateTime(ZonedDateTime.now()).build();

		entityManager.persist(dateTimeEntity);

		entityManager.flush();

		entityManager.detach(dateTimeEntity);

		SupportedDateTimeEntity dateTimeEntity2 = entityManager.find(SupportedDateTimeEntity.class,
				dateTimeEntity.getEntityId());

		System.out.println(dateTimeEntity2);

		transaction.commit();
	}

	private static class EntityManagerHolder {
		private static final EntityManager ENTITY_MANAGER = new JpaEntityManagerFactory(
				new Class[] { SupportedDateTimeEntity.class }).getEntityManager();
	}

	public static EntityManager getJpaEntityManager() {
		return EntityManagerHolder.ENTITY_MANAGER;
	}

}
