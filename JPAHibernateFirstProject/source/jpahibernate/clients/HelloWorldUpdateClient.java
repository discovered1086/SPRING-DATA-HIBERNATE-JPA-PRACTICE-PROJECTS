package jpahibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import jpahibernate.model.HelloWorld;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by kings on 25-Feb-17.
 */
public class HelloWorldUpdateClient {


    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new SpringHibernateUtil();

        Session session = hibernateUtil.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //HelloWorld helloWorldAnnotations = session.get(HelloWorld.class, 2);
        //helloWorldAnnotations.setText("Hello World with Hibernate Annotations edition 2 - just got updated");

        HelloWorld helloWorld2 = session.get(HelloWorld.class, 3);
        session.delete(helloWorld2);

        transaction.commit();
        session.close();
    }
}
