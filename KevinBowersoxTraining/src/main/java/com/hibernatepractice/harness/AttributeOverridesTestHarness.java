package com.hibernatepractice.harness;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.Address;
import com.hibernatepractice.model.BankEntity;

public class AttributeOverridesTestHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
			
			Address address = Address.builder().addressLine1("1525 Busch Pkwy").addressLine2("Room No 327")
			.city("Buffalo Grove")
			.state("IL")
			.zipCode("60089")
			.build();
			
			Address address2 = Address.builder().addressLine1("771 Shady Grove Ln")
					.city("Buffalo Grove")
					.state("IL")
					.zipCode("60089")
					.build();
			
			Map<String, String> locations = new HashMap<>();
			locations.put("HCT", "Hartford CT");
			locations.put("CIL", "Chicago IL");

			BankEntity bank = BankEntity.builder()
					.bankName("Bank of America")
					.establishedDate(LocalDate.of(1999, Month.OCTOBER, 16))
					.contactNames(Arrays.asList("Kingshuk Mukherjee", "Deeksha Banerjee"))
					.address(Arrays.asList(address, address2))
					.locations(locations)
					.build();

			session.save(bank);
			
			session.getTransaction().commit();
			
			session.refresh(bank);
			
			System.out.println(bank);

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
