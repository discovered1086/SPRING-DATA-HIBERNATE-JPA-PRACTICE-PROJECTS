package com.hibernatepractice.harness;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;


public class RetrievingNonExistentDataUsingLoadHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			BankEntity bank = session.load(BankEntity.class, 123l);

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
