package com.hibernatepractice.harness.hibernate;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.model.FinancialAccountEntity;


public class RemovingDataInPCHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
			
			FinancialAccountEntity account = session.get(FinancialAccountEntity.class, 1l);
			
			session.remove(account);

			BankEntity bank = session.load(BankEntity.class, 1l);

			System.out.println("Method executed"
					+ (session.contains(bank) ? " and data loaded into the persistence context" : ""));

			System.out.println("Before removal "+session.contains(bank));
			session.delete(bank);
			System.out.println("After removal "+session.contains(bank));

			session.getTransaction().commit();

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
