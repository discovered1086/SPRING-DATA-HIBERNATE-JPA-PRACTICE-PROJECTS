package com.kingshuk.hibernateandjpa.collectionmapping.harness;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.collectionmapping.configuration.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.collectionmapping.entities.Address;
import com.kingshuk.hibernateandjpa.collectionmapping.entities.Role;
import com.kingshuk.hibernateandjpa.collectionmapping.entities.Role.RoleName;
import com.kingshuk.hibernateandjpa.collectionmapping.entities.UserProfile;

public class ElementCollectionPracticeHarness {

	public static void main(String[] args) {
		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();
			
			UserProfile userProfile = new UserProfile();
			userProfile.setEmailAddress("sly.mania42@gmail.com");
			userProfile.setUserId("rishi2616");
			
			Address address = new Address();
			address.setAddressLine1("771 Shady Grove Ln");
			address.setCity("Buffalo Grove");
			address.setState("IL");
			address.setZipCode("60089");
			
			Role role1 = new Role();
			role1.setRoleName(RoleName.ADMINISTRATOR);
			role1.setRoleDescription("This is an administrative role");
			
			Role role2 = new Role();
			role2.setRoleName(RoleName.CUSTOMER_SERVICE);
			role2.setRoleDescription("This is a customer service role");
			
			userProfile.setAddress(address);
			
			userProfile.getRoles().add(role2);
			userProfile.getRoles().add(role1);
			
			session.save(userProfile);
			
			transaction.commit();

		} catch(Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
