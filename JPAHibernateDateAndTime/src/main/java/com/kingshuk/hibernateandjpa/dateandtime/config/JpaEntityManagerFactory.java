package com.kingshuk.hibernateandjpa.dateandtime.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

import oracle.jdbc.pool.OracleDataSource;

public class JpaEntityManagerFactory {
	private String DB_URL = "jdbc:oracle:thin:@//kingsdatabase.csum1qcusypo.us-east-2.rds.amazonaws.com/kingsdb1";
	private String DB_USER_NAME = "hibernate_practice";
	private String DB_PASSWORD = "Iofdtiger#16";
	private Class[] entityClasses;

	public JpaEntityManagerFactory(Class[] entityClasses) {
		this.entityClasses = entityClasses;
	}

	public EntityManager getEntityManager(){
		try {
			return getEntityManagerFactory().createEntityManager();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected EntityManagerFactory getEntityManagerFactory() throws SQLException {
		PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo(getClass().getSimpleName());
		Map<String, Object> configuration = new HashMap<>();
		return new EntityManagerFactoryBuilderImpl(new PersistenceUnitInfoDescriptor(persistenceUnitInfo),
				configuration).build();
	}

	protected HibernatePersistenceUnitInfo getPersistenceUnitInfo(String name) throws SQLException {
		return new HibernatePersistenceUnitInfo(name, getEntityClassNames(), getProperties());
	}

	protected List<String> getEntityClassNames() {
		return Arrays.asList(getEntities()).stream().map(Class::getName).collect(Collectors.toList());
	}

	protected Properties getProperties() throws SQLException {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
		properties.put("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.jdbc.time_zone", "UTC");
		properties.put("hibernate.connection.datasource", getOracleDataSource());
		return properties;
	}

	protected Class[] getEntities() {
		return entityClasses;
	}

	protected DataSource getOracleDataSource() throws SQLException {
		OracleDataSource mysqlDataSource = new OracleDataSource();
		mysqlDataSource.setURL(DB_URL);
		mysqlDataSource.setUser(DB_USER_NAME);
		mysqlDataSource.setPassword(DB_PASSWORD);
		return mysqlDataSource;
	}
}
