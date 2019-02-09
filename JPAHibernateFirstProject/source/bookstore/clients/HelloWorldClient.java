package bookstore.clients;

import bookstore.model.dao.HelloWorldAnnotations;
import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import org.hibernate.Session;

/**
 * Created by kings on 24-Feb-17.
 */
public class HelloWorldClient {

    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new SpringHibernateUtil();

        Session session = hibernateUtil.createSessionFactory().openSession();
        session.beginTransaction();

        //HelloWorld helloWorld=new HelloWorld("Hello World with Hibernate XML");
        HelloWorldAnnotations helloWorld = new HelloWorldAnnotations("Hello World with Hibernate Annotations edition 2");

        session.save(helloWorld);

        session.getTransaction().commit();
        session.close();
    }
}
