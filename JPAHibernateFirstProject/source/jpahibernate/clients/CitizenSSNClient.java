package jpahibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import jpahibernate.model.Citizen;
import jpahibernate.model.SSNEntry;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by kings on 26-Feb-17.
 */
public class CitizenSSNClient {

    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();
        try {
            session = hibernateUtil.createSessionFactory().openSession();
            transaction = session.beginTransaction();

            SSNEntry ssnEntry = new SSNEntry(564654564);
            Citizen citizen = new Citizen("Deeksha Banerjee", ssnEntry);

            session.persist(citizen);

            transaction.commit();
        } catch (HibernateException ex) {
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
