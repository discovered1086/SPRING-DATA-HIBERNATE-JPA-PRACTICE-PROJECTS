package com.kingshuk.hibernateandjpa.utility;

import org.hibernate.cfg.Configuration;

public class ConfigurationUtil {
	
	private ConfigurationUtil() {
		
	}

	public static Configuration getConfiguration() {
		Configuration configuration = null;

		try {
			configuration = new Configuration();
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect")
					.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")
					.setProperty("hibernate.connection.url",
							"jdbc:oracle:thin:@//kingsdatabase.csum1qcusypo.us-east-2.rds.amazonaws.com/kingsdb1")
					.setProperty("hibernate.connection.username", "hibernate_practice")
					.setProperty("hibernate.connection.password", "Iofdtiger#16")
					.setProperty("hibernate.show_sql", "true")
					//.setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.format_sql", "true");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return configuration;
	}

}
