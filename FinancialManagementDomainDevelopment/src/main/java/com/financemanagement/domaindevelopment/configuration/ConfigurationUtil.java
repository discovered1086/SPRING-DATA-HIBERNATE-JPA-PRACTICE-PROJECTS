package com.financemanagement.domaindevelopment.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.financemanagement.domaindevelopment.allmodels.enums.old.BankAccountType;
import com.financemanagement.domaindevelopment.allmodels.enums.old.CreditCardNetWork;
import com.financemanagement.domaindevelopment.allmodels.model.old.AccountEntity;
import com.financemanagement.domaindevelopment.allmodels.model.old.AddressEntity;
import com.financemanagement.domaindevelopment.allmodels.model.old.BankAccountEntity;
import com.financemanagement.domaindevelopment.allmodels.model.old.CategoryEntity;
import com.financemanagement.domaindevelopment.allmodels.model.old.CreditCardAccountEntity;
import com.financemanagement.domaindevelopment.allmodels.model.old.CustomerEntity;
import com.financemanagement.domaindevelopment.allmodels.model.old.TransactionEntity;

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
					.addAnnotatedClass(AccountEntity.class).addAnnotatedClass(AddressEntity.class)
					.addAnnotatedClass(BankAccountEntity.class).addAnnotatedClass(BankAccountType.class)
					.addAnnotatedClass(CategoryEntity.class).addAnnotatedClass(CreditCardAccountEntity.class)
					.addAnnotatedClass(CustomerEntity.class).addAnnotatedClass(TransactionEntity.class)
					.addAnnotatedClass(CreditCardNetWork.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return configuration != null ? configuration.buildSessionFactory() : null;
	}

}
