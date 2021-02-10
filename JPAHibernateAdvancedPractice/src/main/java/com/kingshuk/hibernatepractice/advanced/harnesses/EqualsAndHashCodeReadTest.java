package com.kingshuk.hibernatepractice.advanced.harnesses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingshuk.hibernatepractice.advanced.configuration.ConfigurationUtil;
import com.kingshuk.hibernatepractice.advanced.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.File;
import java.util.Objects;

public class EqualsAndHashCodeReadTest {
	
	
	public static void main(String[] args) {

		Transaction theTransaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();
				Session session = Objects.requireNonNull(sessionFactory).openSession()) {
			
			//Load the object to memory from the json file
			ObjectMapper objectMapper = new ObjectMapper();

			// Begin the transaction
			theTransaction = session.beginTransaction();

			final Vehicle vehicle = session.find(Vehicle.class, 284);

			Vehicle vehicle2 = objectMapper.readValue(
					new File("jsonFiles/vehicle.json"), Vehicle.class);

			session.save(vehicle2);

			theTransaction.commit();
			// Commit the transaction
		} catch (Exception e) {
			e.printStackTrace();

			if (theTransaction != null) {
				theTransaction.rollback();
			}
		}
	}

}
