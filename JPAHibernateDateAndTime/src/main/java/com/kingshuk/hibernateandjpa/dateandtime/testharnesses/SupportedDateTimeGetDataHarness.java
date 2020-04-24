package com.kingshuk.hibernateandjpa.dateandtime.testharnesses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.dateandtime.config.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.dateandtime.model.SupportedDateTimeEntity;

public class SupportedDateTimeGetDataHarness {
	public static void main(String[] args) {

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			SupportedDateTimeEntity entity = session.get(SupportedDateTimeEntity.class, 183l);

			System.out.println(entity);

			/*System.out.println("Zoned Date time: "+ entity.getZonedDateTime().withZoneSameInstant(ZoneId.of("Asia/Kolkata")));

			System.out.println("Local Date time: "+ entity.getLocalDateTime().atZone(ZoneId.of("Asia/Kolkata")));

			System.out.println(entity.getLocalDateTime().toInstant(ZoneOffset.ofHoursMinutes(05, 30)));*/

			SupportedDateTimeEntity entity2 = session.get(SupportedDateTimeEntity.class, 184l);

			System.out.println(entity2);

			/*System.out.println(entity2.getZonedDateTime().withZoneSameInstant(ZoneId.of("Asia/Kolkata")));

			System.out.println(entity2.getLocalDateTime().atZone(ZoneId.of("Asia/Kolkata")));

			System.out.println(entity2.getLocalDateTime().toInstant(ZoneOffset.ofHoursMinutes(05, 30)));*/
			
			SupportedDateTimeEntity entity3 = session.get(SupportedDateTimeEntity.class, 185l);

			System.out.println(entity3);

			transaction.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
