package jpahibernate.clients;

import jpahibernate.model.Department;
import jpahibernate.model.Employee;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;

/**
 * Created by kings on 04-Mar-17.
 */
public class QueryLanguageClient {
    private static Logger logger = Logger.getLogger(QueryLanguageClient.class);

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

            /*Employee employee = new Employee(4547897,"Amit BIswas","Executionaer", null);
            entityManager.persist(employee);

            */
            /*Query query= entityManager.createQuery("select employee.name  from Employee employee where employee.department.departmentId=null");
            List<String> employeeNameList=(List<String>)query.getResultList();

            for(String name: employeeNameList){
                logger.info("The names are "+name);
            }*/


            /*Querying the whole employee object
            Query query = entityManager.createQuery("select employee  from Employee employee");
            List<Employee> employeeNameList = query.getResultList();

            for (Employee employee : employeeNameList) {
                logger.info("The name is " + employee.getName());
                logger.info("The employee ID is " + employee.getEmployeeId());
                logger.info("The designation is " + employee.getDesignation());
                if (employee.getDepartment() != null)
                    logger.info("The department is " + employee.getDepartment().getDepartmentName());
            }*/

            /*Querying few columns, fields of the employee object
            Query query = entityManager.createQuery("select employee.name, employee.designation  from Employee employee");
            List<Object[]> employeeNameList = query.getResultList();

            for (Object[] employee : employeeNameList) {
                logger.info("The name is " + employee[0]);
                //logger.info("The employee ID is " + employee.getEmployeeId());
                logger.info("The designation is " + employee[1]);

            }*/

            /*Department department=new Department("Kuchutepona services");
            entityManager.persist(department);*/

            /*Querying using a dynamic query with inline parameter setting
            Integer employeeId=232649;
            Query query = entityManager.createQuery("select employee  from Employee employee where employee.employeeId="+employeeId);
            List<Employee> employeeNameList = query.getResultList();

            for (Employee employee : employeeNameList) {
                logger.info("The name is " + employee.getName());
                logger.info("The employee ID is " + employee.getEmployeeId());
                logger.info("The designation is " + employee.getDesignation());
                if (employee.getDepartment() != null)
                    logger.info("The department is " + employee.getDepartment().getDepartmentName());
            }*/

            /*Querying using a dynamic query with parameter setting using set parameter
            Integer employeeId=232649;
            Query query = entityManager.createQuery("select employee  from Employee employee where employee.employeeId=:Id");
            query.setParameter("Id",232649);
            List<Employee> employeeNameList = query.getResultList();

            for (Employee employee : employeeNameList) {
                logger.info("The name is " + employee.getName());
                logger.info("The employee ID is " + employee.getEmployeeId());
                logger.info("The designation is " + employee.getDesignation());
               /* if (employee.getDepartment() != null)
                    logger.info("The department is " + employee.getDepartment().getDepartmentName());
            }*/


  /*Querying using a dynamic query with parameter setting using set parameter and chaining method calls
            Integer employeeId=232649;
            List<Employee> employeeNameList = entityManager.createQuery("select employee  from Employee employee where employee.employeeId=:Id")
                                                                                                        .setParameter("Id",232649)
                                                                                                        .getResultList();

            for (Employee employee : employeeNameList) {
                logger.info("The name is " + employee.getName());
                logger.info("The employee ID is " + employee.getEmployeeId());
                logger.info("The designation is " + employee.getDesignation());
               /* if (employee.getDepartment() != null)
                    logger.info("The department is " + employee.getDepartment().getDepartmentName());
            }*/

            /*Querying using a dynamic query with parameter setting using set parameter and chaining method calls
            Integer employeeId=232649;
            List<Object[]> employeeNameList = entityManager.createNamedQuery("findMyEmployee")
                                                                                                        .setParameter("Id",232649)
                                                                                                        .getResultList();

            for (Object[] employee : employeeNameList) {
                logger.info("The name is " + employee[0]);
                //logger.info("The employee ID is " + employee.getEmployeeId());
                logger.info("The designation is " + employee[1]);
               /* if (employee.getDepartment() != null)
                    logger.info("The department is " + employee.getDepartment().getDepartmentName());
            }*/

            /*Querying aggregate functions
            Long integer= (Long)entityManager.createNamedQuery("CountTotalEmployeeIds").getSingleResult();

            logger.info("Total Number of employees are "+integer);*/

            /*Querying using associations*
               List<Object[]> employeeNameList = entityManager.createNamedQuery("findBothEmployerAndDepartment")
                    .setParameter("departmentId", 7)
                    .getResultList();

            for (Object[] employee : employeeNameList) {
                logger.info("The name is " + employee[1]);
                logger.info("The employee ID is " + employee[0]);
                logger.info("The designation is " + employee[2]);
                logger.info("The department is " + employee[3]);
            }*/

               /*Querying using associations and join fetch*
            List<Employee> employeeNameList = entityManager.createNamedQuery("findBothEmployerAndDepartment")
                    .setParameter("employeeId", 232649)
                    .getResultList();

            for (Employee employee : employeeNameList) {
                logger.info("The name is " +employee.getDepartment().getEmployeeList());
               // logger.info("The employee ID is " + employee.getEmployeeId());
                //logger.info("The designation is " + employee.getDesignation());
            }*/

            //The above query and the below query renders the same results

         List<Department> departmentList = entityManager.createNamedQuery("findBothDepartmentAndEmployee")
                   .setParameter("departmentId", 7)
                    .getResultList();

            for (Department department : departmentList) {
                logger.info("The name is " + department.getEmployeeList());
            }
            // logger.info("The employee ID is " + employee.getEmployeeId());
            //logger.info("The designation is " + employee.getDesignation());


            transaction.commit();
            //entityManager.close();
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
