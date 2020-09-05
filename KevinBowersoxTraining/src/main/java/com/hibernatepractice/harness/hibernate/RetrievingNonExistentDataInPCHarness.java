package com.hibernatepractice.harness.hibernate;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;


public class RetrievingNonExistentDataInPCHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			BankEntity bank = session.get(BankEntity.class, 123l);

			System.out.println("Method executed"
					+ (session.contains(bank) ? " and data loaded into the persistence context" : null));

			System.out.println("The bank name is " + bank.getBankName());

			session.getTransaction().commit();

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
