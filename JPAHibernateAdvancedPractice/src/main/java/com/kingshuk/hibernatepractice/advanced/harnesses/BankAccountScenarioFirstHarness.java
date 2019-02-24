package com.kingshuk.hibernatepractice.advanced.harnesses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernatepractice.advanced.configuration.ConfigurationUtil;

public class BankAccountScenarioFirstHarness {

	public static void main(String[] args) {

		Transaction theTransaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();
				Session session = sessionFactory.openSession()) {

			// Begin the transaction
			theTransaction = session.beginTransaction();

			// Commit the transaction
			theTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();

			if (theTransaction != null) {
				theTransaction.rollback();
			}
		}
	}

}
