package commonutility;

import com.kingshuk.hibernate.cognizanttraining.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CognizantHibernateUtilUpdated extends HibernateUtil {
    @Override
    public SessionFactory createSessionFactory() {
        Configuration configuration=null;
        try{
            configuration=new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect")
                    .setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")
                    .setProperty("hibernate.connection.url", "jdbc:oracle:thin:@//192.168.0.9:1521/mydatabase")
                    .setProperty("hibernate.connection.username", "jpa_training")
                    .setProperty("hibernate.connection.password", "Iofdtiger#16")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .addAnnotatedClass(BookInventory.class)
                    .addAnnotatedClass(BookCustomerCompositeKey.class)
                    .addAnnotatedClass(CustomerAddress.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(BookIssue.class)
                    .addAnnotatedClass(Reservation.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return configuration != null ? configuration.buildSessionFactory() : null;
    }
}
