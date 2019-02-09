package com.kingshuk.persistence.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {

    private StandardServiceRegistry serviceRegistry;

    public SessionFactory createAndReturnSessionFactory() {
        SessionFactory sessionFactory = null;
        StandardServiceRegistryBuilder registryBuilder = null;

        try {
            //Step 1: create a new instance of the service registry builder
            registryBuilder = new StandardServiceRegistryBuilder();

            //Step 2: Create a Map of hibernate properties for the builder
            Map<String, String> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
            settings.put(Environment.URL, "jdbc:oracle:thin:@//192.168.0.11:1521/mydatabase");
            settings.put(Environment.USER, "hibernate_practice");
            settings.put(Environment.PASS, "Iofdtiger#16");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            //Step 3: Apply the settings on the builder
            registryBuilder.applySettings(settings);

            //Step 4: Create the registry
            serviceRegistry = registryBuilder.build();

            //Step 5: Create the meta data sources instance and pass on the registry
            MetadataSources sources = new MetadataSources(serviceRegistry);

            //Step 6: Create the meta data
            Metadata metadata = sources.getMetadataBuilder().build();

            //Step 7: create the session factory from the meta data
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            ex.printStackTrace();

            //If anything goes wrong we have to destroy the registry
            if (serviceRegistry != null) {
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
            }
        }

        return sessionFactory;
    }
}
