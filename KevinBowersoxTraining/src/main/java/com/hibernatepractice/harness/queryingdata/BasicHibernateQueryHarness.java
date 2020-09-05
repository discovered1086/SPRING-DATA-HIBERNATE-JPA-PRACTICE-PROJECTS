package com.hibernatepractice.harness.queryingdata;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.TransactionEntity;

public class BasicHibernateQueryHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
		
			Query<TransactionEntity> query = session.createQuery("from TransactionEntity t order by t.transactionNotes",
					TransactionEntity.class);
			
			List<TransactionEntity> resultList = query.getResultList();
			
			resultList.forEach(System.out::println);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
