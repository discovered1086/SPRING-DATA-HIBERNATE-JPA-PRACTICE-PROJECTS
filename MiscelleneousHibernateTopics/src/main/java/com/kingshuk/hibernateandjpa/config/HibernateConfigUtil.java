package com.kingshuk.hibernateandjpa.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernateandjpa.mappingwithmapsidannotation.Employee;
import com.kingshuk.hibernateandjpa.mappingwithmapsidannotation.EmployeePassport;

public class HibernateConfigUtil {

	private HibernateConfigUtil() {

	}

	// Singleton implementation of session factory
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Employee.class).addAnnotatedClass(EmployeePassport.class);
			return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
