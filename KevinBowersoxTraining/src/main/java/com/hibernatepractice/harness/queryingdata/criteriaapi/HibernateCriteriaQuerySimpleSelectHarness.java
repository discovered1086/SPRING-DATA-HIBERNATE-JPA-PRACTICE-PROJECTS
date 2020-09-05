package com.hibernatepractice.harness.queryingdata.criteriaapi;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.TransactionEntity;

public class HibernateCriteriaQuerySimpleSelectHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

			CriteriaQuery<TransactionEntity> createQuery = criteriaBuilder.createQuery(TransactionEntity.class);
			
			Root<TransactionEntity> root = createQuery.from(TransactionEntity.class);

			createQuery.select(root);

			Query<TransactionEntity> createQuery2 = session.createQuery(createQuery);

			List<TransactionEntity> resultList = createQuery2.getResultList();

			resultList.forEach(System.out::println);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
