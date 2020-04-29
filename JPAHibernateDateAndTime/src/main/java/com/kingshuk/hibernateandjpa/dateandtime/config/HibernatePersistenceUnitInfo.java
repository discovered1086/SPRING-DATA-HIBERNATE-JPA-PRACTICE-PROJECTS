package com.kingshuk.hibernateandjpa.dateandtime.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import lombok.Getter;

@Getter
public class HibernatePersistenceUnitInfo implements PersistenceUnitInfo {
	public static String JPA_VERSION = "2.2";
	private String persistenceUnitName;
	private PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
	private List<String> managedClassNames;
	private List<String> mappingFileNames = new ArrayList<>();
	private Properties properties;
	private DataSource jtaDataSource;
	private DataSource nonjtaDataSource;
	private List<ClassTransformer> transformers = new ArrayList<>();

	public HibernatePersistenceUnitInfo(String persistenceUnitName, List<String> managedClassNames,
			Properties properties) {
		this.persistenceUnitName = persistenceUnitName;
		this.managedClassNames = managedClassNames;
		this.properties = properties;
	}

	@Override
	public String getPersistenceProviderClassName() {
		return null;
	}

	@Override
	public DataSource getNonJtaDataSource() {
		return null;
	}

	@Override
	public List<URL> getJarFileUrls() {
		return null;
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		return null;
	}

	@Override
	public boolean excludeUnlistedClasses() {
		return false;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
		return null;
	}

	@Override
	public ValidationMode getValidationMode() {
		return null;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		return null;
	}

	@Override
	public void addTransformer(ClassTransformer transformer) {
		//Intentionally left blank
	}

	@Override
	public ClassLoader getNewTempClassLoader() {
		return null;
	}
}
