package com.hibernatepractice.harness;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.Address;
import com.hibernatepractice.model.ComplexUser;

public class EmbeddableTestHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
			
			Address address = Address.builder().addressLine1("1525 Busch Pkwy").addressLine2("Room No 327")
			.city("Buffalo Grove")
			.state("IL")
			.zipCode("60089")
			.build();

			ComplexUser user = ComplexUser.builder().firstName("Kingshuk").lastName("Mukherjee").userName("rishi2616")
					.dob(LocalDate.of(1986, Month.OCTOBER, 16))
					.createdDate(LocalDateTime.now()).lastUpdatedDate(LocalDateTime.now())
					.address(address)
					.build();

			session.save(user);
			
			session.getTransaction().commit();
			
			session.refresh(user);
			
			System.out.println(user);

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
