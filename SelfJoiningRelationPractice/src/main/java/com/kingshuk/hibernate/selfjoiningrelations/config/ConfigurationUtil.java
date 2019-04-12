package com.kingshuk.hibernate.selfjoiningrelations.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernate.selfjoiningrelations.entities.LearningTrack;



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
							"jdbc:oracle:thin:@//kingsdatabase.csum1qcusypo.us-east-2.rds.amazonaws.com/kingsdb1")
					.setProperty("hibernate.connection.username", "learning_management")
					.setProperty("hibernate.connection.password", "Iofdtiger#16")
					.setProperty("hibernate.show_sql", "true")
					//.setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.format_sql", "true")
					.addAnnotatedClass(LearningTrack.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return configuration != null ? configuration.buildSessionFactory() : null;
	}

}
