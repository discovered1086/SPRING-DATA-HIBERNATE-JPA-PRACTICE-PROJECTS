package com.kingshuk.hibernateandjpa.dateandtime.testharnesses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.dateandtime.config.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.dateandtime.model.SupportedDateTimeEntity;

public class SupportedDateTimePracticeHarness {
	public static void main(String[] args) {

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			/*
			 * OffsetDateTime offsetDateTime = OffsetDateTime.now()
			 * .withOffsetSameInstant(ZoneOffset.ofHoursMinutes(05, 30));
			 */

			// System.out.println(offsetDateTime);

			SupportedDateTimeEntity dateTimeEntity = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now()).offsetDateTime(OffsetDateTime.now())
					.offsetTime(OffsetTime.now()).zonedDateTime(ZonedDateTime.now()).build();

			session.save(dateTimeEntity);

			transaction.commit();

			Transaction transaction2 = session.beginTransaction();

			session.evict(dateTimeEntity);

			SupportedDateTimeEntity dateTimeEntity2 = session.get(SupportedDateTimeEntity.class,
					dateTimeEntity.getEntityId());

			System.out.println(dateTimeEntity2);

			transaction2.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
