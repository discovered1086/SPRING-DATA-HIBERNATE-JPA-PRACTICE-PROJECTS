package com.kingshuk.hibernateandjpa.exploringmapkeycolumn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.config.ConfigurationUtil;


public class MappingWithMapKeyColumnRunner {

	public static void main(String[] args) {
		Transaction transaction = null;
		
		try (SessionFactory sessionFactory = ConfigurationUtil.getConfiguration()
											 .addAnnotatedClass(LearningPlan.class)
											 .addAnnotatedClass(LearningPlanItem.class)
											 .buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			
			transaction = session.beginTransaction();
			
			LearningPlanItem planItem1 = LearningPlanItem.builder()
							.learningInstructions("Have to learn this when I get a chance.")
									  .build();
			
			LearningPlanItem planItem2 = LearningPlanItem.builder()
								.learningInstructions("Have to learn this when I get a chance second time.")
					  .build();
			
			Map<Integer, LearningPlanItem> learningPlanItems = new HashMap<>();
			
			learningPlanItems.put(000045, planItem1);
			learningPlanItems.put(000046, planItem2);
			
			LearningPlan learningPlan = LearningPlan.builder().learningPlanItems(learningPlanItems)
								  .build();
			
			session.persist(learningPlan);
			
			transaction.commit();
		}catch (Exception e) {
			if (!Objects.isNull(transaction)) {
				transaction.rollback();
			}
		}
	}

}
