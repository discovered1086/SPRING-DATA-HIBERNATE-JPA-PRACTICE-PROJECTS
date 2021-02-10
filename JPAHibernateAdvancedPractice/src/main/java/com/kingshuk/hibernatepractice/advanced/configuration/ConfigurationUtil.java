package com.kingshuk.hibernatepractice.advanced.configuration;

import com.kingshuk.hibernatepractice.advanced.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernatepractice.advanced.enums.BankAccountType;
import com.kingshuk.hibernatepractice.advanced.enums.NetWork;

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
							"jdbc:oracle:thin:@kingsdatabase.csum1qcusypo.us-east-2.rds.amazonaws.com:1521:kingsdb1")
					.setProperty("hibernate.connection.username", "hibernate_practice")
					.setProperty("hibernate.connection.password", "Iofdtiger#16")
					.setProperty("hibernate.show_sql", "true")
					.setProperty("hibernate.format_sql", "true")
					.setProperty("hibernate.hbm2ddl.auto", "update")
//					.addAnnotatedClass(Account.class).addAnnotatedClass(Address.class)
//					.addAnnotatedClass(BankAccount.class).addAnnotatedClass(BankAccountType.class)
//					.addAnnotatedClass(Category.class).addAnnotatedClass(CreditCardAccount.class)
//					.addAnnotatedClass(Customer.class).addAnnotatedClass(Transaction.class)
//					.addAnnotatedClass(NetWork.class)
					.addAnnotatedClass(Vehicle.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return configuration != null ? configuration.buildSessionFactory() : null;
	}

}
