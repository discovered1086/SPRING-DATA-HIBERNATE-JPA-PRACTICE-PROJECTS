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
public class UpdateEngineeringStudentClient {
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

            //Updating the student Object
            theStudent.setStudentName("Deeksha Banerjee");

            transaction.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                session.close();
            }
        }

    }
}
