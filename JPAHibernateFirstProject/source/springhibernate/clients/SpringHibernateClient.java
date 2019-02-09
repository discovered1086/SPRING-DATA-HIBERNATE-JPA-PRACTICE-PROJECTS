package springhibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import springhibernate.model.*;

/**
 * Created by kingshuk on 6/11/17.
 */
public class SpringHibernateClient {
    private static Logger logger = Logger.getLogger(SpringHibernateClient.class);

    public static void main(String[] args) {
        Session session=null;
        Transaction transaction=null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();
        try {
            //session = hibernateUtil.createSessionFactory().getCurrentSession();
            //Have to know what's the difference between opening a new session vs getting the current session
            session=hibernateUtil.createSessionFactory().openSession();
            transaction = session.beginTransaction();

            //First things first
            Discipline discipline1=new Discipline();
            discipline1.setDisciplineName("IT");

            ExamResults examResults1=new ExamResults();
            examResults1.setExamYear("2005");
            examResults1.setDiscipline(discipline1);

            session.persist(examResults1);

            EngCourse course1=new EngCourse("Java",120.00);

            EngStudent student=new EngStudent();
            student.setStudentName("Kingshuk Mukherjee");
            student.setBatchYear(2005);
            student.getCourseList().add(course1);
            student.setDiscipline(discipline1);


            StudentExamResult examResult=new StudentExamResult();
            examResult.setExamResults(examResults1);
            examResult.setTheStudent(student);
            examResult.setPercentile("96%");

            student.getExamResultsList().add(examResult);

            session.persist(examResult);
            session.persist(student);

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
