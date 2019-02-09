package springhibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import springhibernate.model.EngStudent;

import java.util.List;

/**
 * Created by kingshuk on 6/12/17.
 */
public class DeleteEngineeringStudentClient {

    private static Logger logger = Logger.getLogger(SpringHibernateClient.class);

    public static void main(String[] args) {
        Session session= null;
        Transaction transaction=null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();
        try{
            session= hibernateUtil.createSessionFactory().openSession();
            transaction=session.getTransaction();

            transaction.begin();

            List<EngStudent> theStudentsList=(List<EngStudent>)session.createQuery("from EngStudent").getResultList();

            for(EngStudent theStudent:theStudentsList){
                System.out.println("The student name is: "+theStudent.getStudentName());
                session.remove(theStudent);
            }

            //Another and probably a more efficient way of deleting objects
            //session.createQuery("delete from EngStudent").executeUpdate();


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
