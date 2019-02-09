package jpahibernate.clients;

import jpahibernate.model.Department;
import jpahibernate.model.Employee;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by kings on 28-Feb-17.
 */
public class EmpDepEqualsHashcodeClient2 {

    private static Logger logger = Logger.getLogger(EmpDepEqualsHashcodeClient2.class);

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

            Employee employee = entityManager.find(Employee.class, 232649);


            transaction.commit();
            entityManager.close();

            entityManager2 = entityManagerFactory.createEntityManager();
            transaction2 = entityManager2.getTransaction();
            transaction2.begin();

            Department department = entityManager2.find(Department.class, 7);
            List<Employee> employeeList = department.getEmployeeList();

            logger.info("Does the list have Kingshuk's details already? " + employeeList.contains(employee));

            transaction2.commit();
            entityManager2.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
           /* if (entityManager != null) {
                entityManager.close();
            }*/
        }

    }
}
