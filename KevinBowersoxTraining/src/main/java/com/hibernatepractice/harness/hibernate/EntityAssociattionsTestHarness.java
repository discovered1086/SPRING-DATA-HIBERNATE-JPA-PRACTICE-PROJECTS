package com.hibernatepractice.harness.hibernate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.ComplexUser;
import com.hibernatepractice.model.Credential;

public class EntityAssociattionsTestHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			ComplexUser user = ComplexUser.builder().firstName("Kingshuk").lastName("Mukherjee").userName("rishi2616")
					.dob(LocalDate.of(1986, Month.OCTOBER, 16)).createdDate(LocalDateTime.now())
					.lastUpdatedDate(LocalDateTime.now()).build();

			Credential credential = Credential.builder().userName("rishi2616").password("sdjfhsdfgdsbfgdsyfg")
					.user(user).build();

			session.save(credential);

			session.getTransaction().commit();

			session.refresh(user);
			session.refresh(credential);

			System.out.println(user);

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
