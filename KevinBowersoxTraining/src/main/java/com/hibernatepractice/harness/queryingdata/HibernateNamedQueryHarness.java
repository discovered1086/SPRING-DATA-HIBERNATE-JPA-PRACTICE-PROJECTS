package com.hibernatepractice.harness.queryingdata;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.FinancialAccountEntity;

public class HibernateNamedQueryHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
		
			Query<FinancialAccountEntity> query = 
					session.createNamedQuery("transactionAccounts", FinancialAccountEntity.class);
			
			List<FinancialAccountEntity> resultList = query.getResultList();
			
			resultList.forEach(System.out::println);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
