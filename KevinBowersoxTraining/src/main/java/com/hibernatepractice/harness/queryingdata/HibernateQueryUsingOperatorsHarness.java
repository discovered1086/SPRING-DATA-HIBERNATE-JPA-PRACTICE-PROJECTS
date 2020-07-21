package com.hibernatepractice.harness.queryingdata;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.TransactionEntity;

@SuppressWarnings("squid:CommentedOutCodeLine")
public class HibernateQueryUsingOperatorsHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
		
//			Query<TransactionEntity> query = session
//					.createQuery("from TransactionEntity t "
//							+ "where t.transactionAmount < 100 "
//							+ "and t.transactionNotes = 'Coffee from Starbucks' "
//							+ "order by t.transactionNotes ",
//					TransactionEntity.class);
//			
//			List<TransactionEntity> resultList = query.getResultList();
			
			List<TransactionEntity> resultList = session
					.createQuery("from TransactionEntity t "
							+ "where (t.transactionAmount between ?1 and ?2)"
							+ "and t.transactionNotes = 'Coffee from Starbucks' "
							+ "order by t.transactionNotes", TransactionEntity.class)
					.setParameter(1, BigDecimal.valueOf(2.00))
					.setParameter(2, BigDecimal.valueOf(25.00))
					.getResultList();
						
			resultList.forEach(System.out::println);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
