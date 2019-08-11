package com.financemanagement.domaindevelopment.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.financemanagement.domaindevelopment.enums.BankAccountType;
import com.financemanagement.domaindevelopment.enums.NetWork;
import com.financemanagement.domaindevelopment.model.Account;
import com.financemanagement.domaindevelopment.model.Address;
import com.financemanagement.domaindevelopment.model.BankAccount;
import com.financemanagement.domaindevelopment.model.Category;
import com.financemanagement.domaindevelopment.model.CreditCardAccount;
import com.financemanagement.domaindevelopment.model.Customer;
import com.financemanagement.domaindevelopment.model.Transaction;

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
					.addAnnotatedClass(Account.class).addAnnotatedClass(Address.class)
					.addAnnotatedClass(BankAccount.class).addAnnotatedClass(BankAccountType.class)
					.addAnnotatedClass(Category.class).addAnnotatedClass(CreditCardAccount.class)
					.addAnnotatedClass(Customer.class).addAnnotatedClass(Transaction.class)
					.addAnnotatedClass(NetWork.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return configuration != null ? configuration.buildSessionFactory() : null;
	}

}
