package com.kingshuk.hibernateandjpa.collectionmapping.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernateandjpa.collectionmapping.entities.Address;
import com.kingshuk.hibernateandjpa.collectionmapping.entities.Role;
import com.kingshuk.hibernateandjpa.collectionmapping.entities.UserProfile;



public class ConfigurationUtil {
	
	private ConfigurationUtil() {
		
	}

	public static SessionFactory buildSessionFactory() {
		Configuration configuration = null;

		try {
			configuration = new Configuration();
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect")
					.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")
					.setProperty("hibernate.connection.url",
							"jdbc:oracle:thin:@//kingsdatabases.csum1qcusypo.us-east-2.rds.amazonaws.com/kingsdb1")
					.setProperty("hibernate.connection.username", "hibernate_practice")
					.setProperty("hibernate.connection.password", "Iofdtiger#16")
					.setProperty("hibernate.show_sql", "true")
					.setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.format_sql", "true")
					.addAnnotatedClass(UserProfile.class)
					.addAnnotatedClass(Address.class)
					.addAnnotatedClass(Role.class);
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return configuration != null ? configuration.buildSessionFactory() : null;
	}

}
