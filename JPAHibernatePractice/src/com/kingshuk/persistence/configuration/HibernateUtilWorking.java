package com.kingshuk.persistence.configuration;

import com.kingshuk.persistence.studentmanagement.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilWorking {

    public SessionFactory creteSessionFactory(){
        Configuration configuration=null;
        try{
            configuration=new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect")
                    .setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver")
                    .setProperty("hibernate.connection.url", "jdbc:oracle:thin:@//192.168.0.11:1521/mydatabase")
                    .setProperty("hibernate.connection.username", "hibernate_practice")
                    .setProperty("hibernate.connection.password", "Iofdtiger#16")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .addAnnotatedClass(Employee.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return configuration != null ? configuration.buildSessionFactory() : null;
    }
}
