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
 * Created by kings on 12-Mar-17.
 */
public class MergingDetachedObjectsClient {

    private static Logger logger = Logger.getLogger(MergingDetachedObjectsClient.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityManager entityManager2 = null;
        EntityTransaction transaction = null;
        EntityTransaction transaction2 = null;
        Employee employee = null;
        Department department = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPAProject");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            department = entityManager.find(Department.class, 7);
            int employeeListSize = department.getEmployeeList().size();


            for (Employee employee1 : department.getEmployeeList()) {
                if (employee1.getEmployeeId() == 232649)
                    employee = employee1;
            }

            transaction.commit();
            entityManager.close();

            department.setDepartmentName("Insurance service Practices");
            employee.setDesignation("Senior dev");

            entityManager2 = entityManagerFactory.createEntityManager();
            transaction2 = entityManager2.getTransaction();
            transaction2.begin();

            Department department1 = entityManager2.merge(department);

            transaction2.commit();
            entityManager2.close();

            //entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            /*if (entityManager != null) {
                //entityManager.flush();
                entityManager.close();
            }*/
        }

    }
}
