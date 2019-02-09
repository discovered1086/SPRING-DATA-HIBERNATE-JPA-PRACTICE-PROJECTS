package jpahibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import jpahibernate.model.Course;
import jpahibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 26-Feb-17.
 */
public class StudentCourseClient {

    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();
        try {
            session = hibernateUtil.createSessionFactory().openSession();
            transaction = session.beginTransaction();

            Course course1=new Course("JPA and Hibernate",190.00);
            Course course2=new Course("Data Modelling workshop",180.00);

            Student student1 =new Student("Kingshuk Mukherjee",2005,"IT");
            List<Course> courseList=new ArrayList<Course>();
            courseList.add(course1);
            courseList.add(course2);
            student1.setCourseList(courseList);

            Student student2 =new Student("Deeksha Banerjee",2008,"CSE");
            List<Course> courseList2=new ArrayList<Course>();
            courseList.add(course1);
            student2.setCourseList(courseList2);

            session.persist(student1);
            session.persist(student2);


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
