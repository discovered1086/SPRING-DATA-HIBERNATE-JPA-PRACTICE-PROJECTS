package com.kingshuk.hibernateandjpa.dateandtime.testharnesses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

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

			OffsetDateTime offsetDateTime = OffsetDateTime.now()
					.withOffsetSameInstant(ZoneOffset.ofHoursMinutes(05, 30));
			
			session.setProperty("hibernate.jdbc.time_zone", "America/New_York");
			
			System.out.println(offsetDateTime);

			session.save(SupportedDateTimeEntity.builder().localDate(LocalDate.now()).localTime(LocalTime.now())
					.localDateTime(LocalDateTime.now()).offsetDateTime(offsetDateTime).offsetTime(OffsetTime.now())
					.build());

			transaction.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
