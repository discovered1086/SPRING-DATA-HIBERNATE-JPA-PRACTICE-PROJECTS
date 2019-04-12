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

public class SelfJoiningHarness {

	public static void main(String[] args) {
		LearningTrack learningTrack = LearningTrack.builder().trackName("Java - The language")
				.trackDescription("This track is for consolidating knowledge on the language")
				.subTracks(new ArrayList<LearningTrack>()).build();

		LearningTrack learningTrack1 = LearningTrack.builder().trackName("Java data types")
				.trackDescription("This track is for consolidating knowledge on the language").subTracks(null).build();

		LearningTrack learningTrack2 = LearningTrack.builder().trackName("Java annotations")
				.trackDescription("This track is for consolidating knowledge on the language").subTracks(null).build();

		learningTrack.getSubTracks().add(learningTrack1);
		learningTrack.getSubTracks().add(learningTrack2);

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();
				Session session = sessionFactory.openSession()) {

			transaction = session.beginTransaction();

			session.persist(learningTrack);

			transaction.commit();

			transaction.begin();

			Query<LearningTrack> query = session.createQuery("SELECT FROM LearningTrack");

			List<LearningTrack> resultList = query.getResultList();

			System.out.println(resultList);

		} catch (Exception e) {
			if (!Objects.isNull(transaction)) {
				transaction.rollback();
			}
		}

	}

}
