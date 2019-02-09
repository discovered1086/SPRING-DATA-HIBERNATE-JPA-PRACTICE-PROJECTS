package jpahibernate.clients;

import jpahibernate.model.Department;
import jpahibernate.model.Employee;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kings on 28-Feb-17.
 */
public class EmpDepEqualsHashcodeClient {
    private static Logger logger = Logger.getLogger(EmpDepEqualsHashcodeClient.class);

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

            //#Task 1
            /*Department department=entityManager.find(Department.class,7);
            Employee employee=new Employee(4578956,"Indranil Chatterjee", "Project Manager",department);
            Employee employee1=new Employee(4578956, "Sabyasachi Chowdhury", "Architect", department);

            logger.info("The two employees are equal? "+ employee.equals(employee1));

            Set<Employee> employeeSet=new HashSet<Employee>();
            employeeSet.add(employee);

            logger.info("Does the set contain both of them?  "+ employeeSet.contains(employee1));

            //logger.info("The first employee is "+employeeList.get(0).getName());*/

            //#Task 2

            Employee employee=entityManager.find(Employee.class, 232649);


            //logger.info("Are the two objects same? "+employee.equals(employee1));

            transaction.commit();
            entityManager.close();

            entityManager2=entityManagerFactory.createEntityManager();
            transaction2=entityManager2.getTransaction();
            transaction2.begin();

            Employee employee1=entityManager2.find(Employee.class,232649);

            logger.info("Are the two objects same? "+employee.equals(employee1));

            transaction2.commit();
            entityManager2.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            /*if (entityManager != null) {
                entityManager.close();
            }*/
        }

    }
}
