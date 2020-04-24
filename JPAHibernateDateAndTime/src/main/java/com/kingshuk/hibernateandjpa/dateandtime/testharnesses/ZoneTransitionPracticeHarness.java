package com.kingshuk.hibernateandjpa.dateandtime.testharnesses;
/**
 * This class has been created to practice the effect of offset transition
 * during or after day light savings time and how the 
 * entry and query to the database is impacted by that
 * 
 * @author Kingshuk Mukherjee
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.dateandtime.config.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.dateandtime.model.SupportedDateTimeEntity;

public class ZoneTransitionPracticeHarness {
	private static final String ASIA_KOLKATA = "Asia/Kolkata";

	public static void main(String[] args) {

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			ZoneId zone = ZoneId.of("America/Chicago");

			ZonedDateTime dt = ZonedDateTime.of(2019, Month.NOVEMBER.getValue(), 03, 01, 30, 0, 0, zone);

			ZonedDateTime dt2 = ZonedDateTime.of(2020, Month.MARCH.getValue(), 8, 01, 30, 0, 0, zone);

			System.out.println(dt);

			SupportedDateTimeEntity dateTimeEntity = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now(ZoneId.of(ASIA_KOLKATA)))
					.offsetDateTime(dt.toOffsetDateTime()).offsetTime(OffsetTime.now()).zonedDateTime(dt).build();

			session.save(dateTimeEntity);

			SupportedDateTimeEntity dateTimeEntity2 = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now(ZoneId.of(ASIA_KOLKATA)))
					.offsetDateTime(dt.toOffsetDateTime().plusHours(1l)).offsetTime(OffsetTime.now())
					.zonedDateTime(dt.plusHours(1l)).build();

			session.save(dateTimeEntity2);

			SupportedDateTimeEntity dateTimeEntity3 = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now(ZoneId.of(ASIA_KOLKATA)))
					.offsetDateTime(dt2.toOffsetDateTime()).offsetTime(OffsetTime.now()).zonedDateTime(dt2).build();

			session.save(dateTimeEntity3);

			SupportedDateTimeEntity dateTimeEntity4 = SupportedDateTimeEntity.builder().localDate(LocalDate.now())
					.localTime(LocalTime.now()).localDateTime(LocalDateTime.now(ZoneId.of(ASIA_KOLKATA)))
					.offsetDateTime(dt2.toOffsetDateTime().plusHours(1l)).offsetTime(OffsetTime.now())
					.zonedDateTime(dt2.plusHours(1l)).build();

			session.save(dateTimeEntity4);

			transaction.commit();

			Transaction transaction2 = session.beginTransaction();

			TypedQuery<SupportedDateTimeEntity> typedQuery = session.createQuery("FROM SupportedDateTimeEntity",
					SupportedDateTimeEntity.class);

			typedQuery.getResultStream().forEach(System.out::println);

			transaction2.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

}
