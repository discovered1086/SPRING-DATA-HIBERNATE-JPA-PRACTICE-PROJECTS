package springmvcupdated.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import springmvcupdated.entity.Discipline;
import springmvcupdated.entity.SemesterExam;
import springmvcupdated.entity.SemesterType;
import springmvcupdated.entity.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kingshuk on 6/11/17.
 */
public class SpringHibernateClient {
    private static Logger logger = Logger.getLogger(SpringHibernateClient.class);

    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();
        try {
            //session = hibernateUtil.createSessionFactory().getCurrentSession();
            //Have to know what's the difference between opening a new session vs getting the current session
            session = hibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            //First things first
            SemesterExam exam = new SemesterExam();
            exam.setDiscipline(Discipline.CSE);
            exam.setSemesterType(SemesterType.ODD);
            exam.setSemesterYear("2005");

            /*DisciplineSubjects subjects = new DisciplineSubjects();
            subjects.setSubject("java");
            subjects.setSubjectDescription("Java basic course");
            subjects.setSubjectPrice(150.00);


            StudentExamResults results=new StudentExamResults();
            results.setGrade("A");*/

            Student student = new Student();
            student.setBatchYear(2005);
            student.setFirstName("Avik");
            student.setLastName("Mallick");
            student.setUserId(Discipline.IT + "2005" + "45");
            student.setPassword("password");
            student.setEmailAddress("IT200545@mckv.org");
            student.setDiscipline(Discipline.IT);
            student.setRollNo(40);
            student.setDOB(getDateFromString("12/18/1987"));


            session.persist(student);
            /*results.setStudent(student);

            SubjectSemesterMapping subjectSemesterMapping=new SubjectSemesterMapping();
            subjectSemesterMapping.setDisciplineSubjects(subjects);
            subjectSemesterMapping.setExam(exam);
            subjectSemesterMapping.getStudentsEnrolledList().add(student);

            results.setSubjectSemesterMapping(subjectSemesterMapping);

            session.persist(subjectSemesterMapping);
            session.persist(results);*/

            /*AdminUser adminUser=new AdminUser();
            adminUser.setDOH(getDateFromString("03/29/2010"));
            adminUser.setEmployeeCode(EmployeeCode.STDNTADM);
            adminUser.setEmployeeId(232649);
            adminUser.setFirstName("Deeksha");
            adminUser.setLastName("Banerjee");
            adminUser.setEmailAddress("deeksha.jiya@gmail.com");
            adminUser.setUserId("rishi1626");
            adminUser.setPassword("password");
            adminUser.setDOB(getDateFromString("10/26/1986"));

            session.persist(adminUser);*/

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("An error occurred " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }


    }

    private static Date getDateFromString(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
