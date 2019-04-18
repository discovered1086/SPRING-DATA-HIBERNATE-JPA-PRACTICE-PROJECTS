package com.kingshuk.hibernateandjpa.simplemapkeycolumn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.config.ConfigurationUtil;

public class SimpleMapKeyColumnRunner {

	public static void main(String[] args) {
		Transaction transaction = null;
		
		try (SessionFactory sessionFactory = ConfigurationUtil.getConfiguration()
											 .addAnnotatedClass(Country.class)
											 .addAnnotatedClass(State.class)
											 .buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			
			transaction = session.beginTransaction();
			
			State state1 = new State("COLORADO");
			
			State state2 = new State("ANTIGUA");
			
			Long state1Id = (Long) session.save(state1);
			
			Long state2Id = (Long) session.save(state2);
			
			Map<Long, State> stateMap = new HashMap<>();
			
			state1 = session.get(State.class, state1Id);
			
			state2 = session.get(State.class, state2Id);
			
			stateMap.put(state1.getId(), state1);
			
			stateMap.put(state2.getId(), state2);
			
			Country country = new Country("US", stateMap);
			
			Long countryId = (Long) session.save(country);
			
			country = session.get(Country.class, countryId);
			
			
			
			System.out.println(country);
			
			transaction.commit();
		}catch (Exception e) {
			if (!Objects.isNull(transaction)) {
				transaction.rollback();
			}
		}
	}

}
