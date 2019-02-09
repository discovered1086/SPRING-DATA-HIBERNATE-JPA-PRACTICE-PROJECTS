package jpahibernate.clients;

import jpahibernate.model.Department;
import jpahibernate.model.Employee;
import jpahibernate.model.HelloWorld;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by kings on 28-Feb-17.
 */
public class EmpDepLazyEagerFetchingClient {
    private static Logger logger = Logger.getLogger(EmpDepLazyEagerFetchingClient.class);

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

            //Employee employee=entityManager.find(Employee.class,232649);
            Department department=entityManager.find(Department.class,7);
            //List<Employee> employeeList=department.getEmployeeList();

            //logger.info("The first employee is "+employeeList.get(0).getName());

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
}
