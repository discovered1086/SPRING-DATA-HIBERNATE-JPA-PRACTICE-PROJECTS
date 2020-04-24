package com.kingshuk.hibernateandjpa.dateandtime.testharnesses;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.dateandtime.config.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.dateandtime.model.SupportedDateTimeEntity;

public class SupportedDateTimeGetAllDataHarness {
	public static void main(String[] args) {

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			TypedQuery<SupportedDateTimeEntity> typedQuery = session.createQuery(
					"FROM SupportedDateTimeEntity sdte ORDER BY sdte.entityId ASC", SupportedDateTimeEntity.class);

			typedQuery.getResultStream().forEach(System.out::println);

			transaction.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
