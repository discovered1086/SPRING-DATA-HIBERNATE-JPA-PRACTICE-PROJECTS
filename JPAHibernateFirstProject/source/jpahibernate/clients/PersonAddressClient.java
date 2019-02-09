package jpahibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import jpahibernate.model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by kings on 25-Feb-17.
 */
public class PersonAddressClient {
    private static HibernateUtil hibernateUtil = null;

    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();

        try {
            session = hibernateUtil.createSessionFactory().openSession();
            transaction = session.beginTransaction();


//        Address address= new Address("2 Park Pl", "Hartford", "CT", "06106");
//        Person person=new Person("Kingshuk Mukherjee",address);
//        session.save(person);

            //Now it's time to retrieve the object

            Person person = session.get(Person.class, 1);
            System.out.println("The address of Mr. " + person.getName() + " is: " + person.getShippingAddress().toString());

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

}
