package com.hibernatepractice.harness;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;

public class FlushMethodHarness {

	public static void main(String[] args) {
		Transaction transaction = null;
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			transaction = session.beginTransaction();

			BankEntity detachedBank = session.load(BankEntity.class, 1l);

			System.out.println("Method executed"
					+ (session.contains(detachedBank) ? " and data loaded into the persistence context" : ""));

			detachedBank.setBankName("Chase Bank");
			System.out.println("Calling flush");
			session.flush();

			detachedBank.setEstablishedDate(LocalDate.of(1989, Month.OCTOBER, 26));

			System.out.println("Calling commit");
			transaction.commit();

			session.close();
		} catch (Exception e) {
			if (Objects.nonNull(transaction)) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
