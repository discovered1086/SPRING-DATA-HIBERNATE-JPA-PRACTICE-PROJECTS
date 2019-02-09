package commonutility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class CognizantTrainingHibernateUtil extends HibernateUtil {
    @Override
    public SessionFactory createSessionFactory() {
        try {
            // Create registry builder
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Map<String, String> settings = new HashMap<String, String>();
            settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
            settings.put(Environment.URL, "jdbc:oracle:thin:@//192.168.0.9:1521/mydatabase");
            settings.put(Environment.USER, "jpa_training");
            settings.put(Environment.PASS, "Iofdtiger#16");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            // Apply settings
            registryBuilder.applySettings(settings);

            // Create registry
            registry = registryBuilder.build();

            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry);

            // Create Metadata
            metaData = sources.getMetadataBuilder().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return metaData != null ? metaData.getSessionFactoryBuilder().build() : null;
    }
}
