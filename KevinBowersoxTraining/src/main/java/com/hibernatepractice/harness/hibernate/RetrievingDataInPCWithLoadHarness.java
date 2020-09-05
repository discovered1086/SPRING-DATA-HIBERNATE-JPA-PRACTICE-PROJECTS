package com.hibernatepractice.harness.hibernate;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;


public class RetrievingDataInPCWithLoadHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			BankEntity bank = session.load(BankEntity.class, 1l);

			System.out.println("Method executed"
					+ (session.contains(bank) ? " and data loaded into the persistence context" : null));

			BankEntity bank2 = session.load(BankEntity.class, 1l);

			System.out.println("The bank name is " + bank.getBankName());

			System.out.println(bank == bank2);

			System.out.println("The second bank name is " + bank2.getBankName());

			session.getTransaction().commit();

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
