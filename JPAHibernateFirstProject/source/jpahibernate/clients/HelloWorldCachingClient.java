package jpahibernate.clients;

import jpahibernate.model.HelloWorld;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by kings on 28-Feb-17.
 */
public class HelloWorldCachingClient {
    private static Logger logger = Logger.getLogger(HelloWorldCachingClient.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityManager entityManager2 = null;
        EntityTransaction transaction = null;
        EntityTransaction transaction2 = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPAProject");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            //HelloWorld helloWorld=new HelloWorld("Hello World with Hibernate XML");
            HelloWorld helloWorld=entityManager.find(HelloWorld.class, 29);
            logger.info("The message is "+helloWorld.getText());

            transaction.commit();
            entityManager.close();

            entityManager2=entityManagerFactory.createEntityManager();
            transaction2=entityManager2.getTransaction();
            transaction2.begin();

            HelloWorld helloWorld1=entityManager2.find(HelloWorld.class, 29);

            logger.info("The second message is"+helloWorld1.getText());

            transaction2.commit();
            entityManager2.close();
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
