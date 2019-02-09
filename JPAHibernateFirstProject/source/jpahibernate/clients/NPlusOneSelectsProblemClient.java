package jpahibernate.clients;

import jpahibernate.model.Employee;
import jpahibernate.model.FeatherWeightUFCFighter;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by kings on 05-Mar-17.
 */
public class NPlusOneSelectsProblemClient {

    private static Logger logger = Logger.getLogger(NPlusOneSelectsProblemClient.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityManager entityManager2 = null;
        EntityTransaction transaction = null;
        EntityTransaction transaction2 = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPAProject");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            List<Employee> employeeList = entityManager.createQuery("select employee from Employee employee").getResultList();
            for (Employee employee : employeeList) {
                logger.info("The name is " + employee.getName());
                logger.info("The designation is  " + employee.getDesignation());
                logger.info("The department name for this employee is  " + employee.getDepartment().getDepartmentName());
            }


            transaction.commit();

            //entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                //entityManager.flush();
                entityManager.close();
            }
        }

    }
}
