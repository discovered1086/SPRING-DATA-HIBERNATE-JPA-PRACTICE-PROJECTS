package jpahibernate.clients;

import commonutility.HibernateUtil;
import commonutility.SpringHibernateUtil;
import jpahibernate.model.Department;
import jpahibernate.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by kings on 26-Feb-17.
 */
public class EmployeeDepartmentClient {

    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        HibernateUtil hibernateUtil = new SpringHibernateUtil();
        try {
            Department department = null;
            session = hibernateUtil.createSessionFactory().openSession();
            transaction = session.beginTransaction();

            department=session.get(Department.class,21);
            Employee employee=session.get(Employee.class,232648);

            //department.getEmployeeList().add(employee); does not work because department is NOT the owner of the relationship and simply doesn't care about the relationship
            //department.setDepartmentName("Life Sciences");
            employee.setDepartment(department);
            //session.persist(department);

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


