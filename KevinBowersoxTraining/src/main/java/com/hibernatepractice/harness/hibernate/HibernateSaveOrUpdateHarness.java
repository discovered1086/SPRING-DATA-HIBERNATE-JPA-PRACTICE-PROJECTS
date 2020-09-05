package com.hibernatepractice.harness.hibernate;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.Address;
import com.hibernatepractice.model.BankEntity;

@SuppressWarnings("squid:S2095")
public class HibernateSaveOrUpdateHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
			
			BankEntity detachedBank = session.load(BankEntity.class, 1l);

			System.out.println("Method executed"
					+ (session.contains(detachedBank) ? " and data loaded into the persistence context" : ""));

			session.getTransaction().commit();

			session.close();
			
			Address address2 = Address.builder().addressLine1("771 Shady Grove Ln").city("Buffalo Grove").state("IL")
					.zipCode("60089").build();

			Map<String, String> locations = new HashMap<>();
			locations.put("CHIL", "Chilwell IL");

			BankEntity transientBank = BankEntity.builder().bankName("KevinsBankEntity of America")
					.establishedDate(LocalDate.of(1999, Month.OCTOBER, 16))
					.contactNames(Arrays.asList("Amit Biswas", "Mainak Biswas"))
					.address(Arrays.asList(address2)).locations(locations).build();
			
			Session session2 = HibernateConfigUtil.getSessionFactory().openSession();
			
			session2.beginTransaction();
			
			System.out.println(session2.contains(detachedBank));
			System.out.println(session2.contains(transientBank));
			
			session2.saveOrUpdate(detachedBank);
			detachedBank.setBankName("Santander KevinsBankEntity");
			
			session2.saveOrUpdate(transientBank);
			
			System.out.println(session2.contains(detachedBank));
			System.out.println(session2.contains(transientBank));
			
			session2.getTransaction().commit();

			session2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
