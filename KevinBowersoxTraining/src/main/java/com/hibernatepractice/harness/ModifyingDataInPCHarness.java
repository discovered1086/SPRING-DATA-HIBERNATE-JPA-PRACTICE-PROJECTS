package com.hibernatepractice.harness;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;


public class ModifyingDataInPCHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			BankEntity bank = session.load(BankEntity.class, 1l);

			System.out.println("Method executed"
					+ (session.contains(bank) ? " and data loaded into the persistence context" : null));

			bank.setBankName("Chase Bank");

			System.out.println("The bank bank name is " + bank.getBankName());

			session.getTransaction().commit();

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
