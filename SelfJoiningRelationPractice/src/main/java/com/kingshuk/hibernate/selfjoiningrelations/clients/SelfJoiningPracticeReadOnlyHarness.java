package com.kingshuk.hibernate.selfjoiningrelations.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.kingshuk.hibernate.selfjoiningrelations.config.ConfigurationUtil;
import com.kingshuk.hibernate.selfjoiningrelations.entities.LearningTrack;

public class SelfJoiningPracticeReadOnlyHarness {

	public static void main(String[] args) {
		
		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();
				Session session = sessionFactory.openSession()) {

			transaction = session.beginTransaction();

			Query<LearningTrack> query = session.createQuery("SELECT a from LearningTrack as a");

			List<LearningTrack> resultList = query.getResultList();

			System.out.println(resultList);
			
			transaction.commit();

		} catch (Exception e) {
			if (!Objects.isNull(transaction)) {
				transaction.rollback();
			}
		}

	}

}
