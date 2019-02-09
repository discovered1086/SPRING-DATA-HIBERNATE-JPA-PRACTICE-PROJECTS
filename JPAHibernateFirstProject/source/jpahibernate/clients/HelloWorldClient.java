package jpahibernate.clients;

import jpahibernate.model.HelloWorld;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by kings on 24-Feb-17.
 */
public class HelloWorldClient {
    private static Logger logger=Logger.getLogger(HelloWorldClient.class);
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        EntityTransaction transaction2 = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPAProject");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            //HelloWorld helloWorld=new HelloWorld("Hello World with Hibernate XML");
            HelloWorld helloWorld = new HelloWorld("Hello World with Hibernate and JPA");
            entityManager.persist(helloWorld);

            transaction.commit();
            entityManager.close();

            helloWorld.setText("Hello World with Hibernate and JPA 2.0");  //Detached java object helloWorld

            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            transaction2 = entityManager1.getTransaction();
            transaction2.begin();

            HelloWorld helloWorld1 = entityManager1.merge(helloWorld);
            logger.info("The current message is: "+helloWorld1.getText());
            transaction2.commit();
            entityManager1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
}
