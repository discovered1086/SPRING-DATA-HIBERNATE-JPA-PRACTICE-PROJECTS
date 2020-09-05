package com.hibernatepractice.harness.hibernate;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;

@SuppressWarnings("squid:S2095")
public class ReattachingDetachedEntitiesHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
			
			BankEntity bank = session.load(BankEntity.class, 1l);

			System.out.println("Method executed"
					+ (session.contains(bank) ? " and data loaded into the persistence context" : ""));

			session.getTransaction().commit();

			session.close();
			
			Session session2 = HibernateConfigUtil.getSessionFactory().openSession();
			
			session2.beginTransaction();
			
			System.out.println(session2.contains(bank));
			session2.update(bank);
			bank.setBankName("Chase KevinsBankEntity");
			System.out.println(session2.contains(bank));
			
			session2.getTransaction().commit();

			session2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
