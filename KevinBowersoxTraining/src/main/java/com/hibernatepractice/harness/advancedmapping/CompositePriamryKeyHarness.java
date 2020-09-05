package com.hibernatepractice.harness.advancedmapping;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.CurrencyEntity;
import com.hibernatepractice.model.CurrencyKey;

public class CompositePriamryKeyHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			Transaction transaction = session.beginTransaction();

			CurrencyEntity entity = CurrencyEntity.builder().countryName("United States").name("Dollar").symbol("USD")
					.build();

			session.save(entity);

			transaction.commit();

			session.close();

			Session session2 = HibernateConfigUtil.getSessionFactory().openSession();

			Transaction transaction2 = session2.beginTransaction();

			CurrencyEntity currencyEntity = session2.get(CurrencyEntity.class,
					new CurrencyKey("Dollar", "United States"));

			System.out.println(currencyEntity);

			transaction2.commit();

			session2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
