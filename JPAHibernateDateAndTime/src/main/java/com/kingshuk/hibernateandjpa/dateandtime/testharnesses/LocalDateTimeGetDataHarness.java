package com.kingshuk.hibernateandjpa.dateandtime.testharnesses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.dateandtime.config.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.dateandtime.model.SupportedDateTimeEntity;

public class LocalDateTimeGetDataHarness {
	public static void main(String[] args) {

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			OffsetDateTime offsetDateTime = OffsetDateTime.now()
					.withOffsetSameInstant(ZoneOffset.ofHoursMinutes(05, 30));

			ZonedDateTime zonedDateTime = offsetDateTime.toZonedDateTime();

			System.out.println(offsetDateTime);

			SupportedDateTimeEntity dateTimeEntity = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now(ZoneId.of("Asia/Kolkata")))
					.offsetDateTime(offsetDateTime).offsetTime(OffsetTime.now()).zonedDateTime(zonedDateTime).build();

			session.save(dateTimeEntity);

			SupportedDateTimeEntity dateTimeEntity2 = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now()).offsetDateTime(offsetDateTime)
					.offsetTime(OffsetTime.now()).zonedDateTime(ZonedDateTime.now()).build();

			session.save(dateTimeEntity2);

			SupportedDateTimeEntity dateTimeEntity3 = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now())
					.offsetDateTime(OffsetDateTime.now(ZoneId.of("Asia/Kolkata"))).offsetTime(OffsetTime.now())
					.zonedDateTime(ZonedDateTime.now()).build();

			session.save(dateTimeEntity3);
			
			SupportedDateTimeEntity dateTimeEntity4 = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now())
					.offsetDateTime(OffsetDateTime.now()).offsetTime(OffsetTime.now())
					.zonedDateTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))).build();

			session.save(dateTimeEntity4);

			transaction.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

}
