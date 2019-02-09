package springhibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import springhibernate.model.EngStudent;

/**
 * Created by kingshuk on 6/12/17.
 */
public class QueryEngineeringStudentClient {

    private static Logger logger = Logger.getLogger(SpringHibernateClient.class);

    public static void main(String[] args) {
        Session session= null;
        Transaction transaction=null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();
        try{
            session=hibernateUtil.createSessionFactory().openSession();
            transaction=session.getTransaction();

            transaction.begin();

            EngStudent theStudent=(EngStudent)session.createQuery("from EngStudent e where e.studentName like '%Kingshuk %'").getResultList().get(0);

            System.out.println("The Student is " +theStudent);

            transaction.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }

    }
}
