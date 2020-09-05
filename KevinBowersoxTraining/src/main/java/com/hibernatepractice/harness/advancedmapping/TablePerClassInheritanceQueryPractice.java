package com.hibernatepractice.harness.advancedmapping;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.inheritance.tableperclass.PortfolioTpcEntity;

public class TablePerClassInheritanceQueryPractice {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			PortfolioTpcEntity savedPortfolio = session.get(PortfolioTpcEntity.class, 202l);
			
			savedPortfolio.getInvestements().forEach(System.out::println);
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
