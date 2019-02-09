package com.kingshuk.persistence.clients;

import com.kingshuk.persistence.configuration.HibernateUtilWorking;
import com.kingshuk.persistence.studentmanagement.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmployeeTestUpdateHarness {

    public static void main(String[] args) {
        Transaction transaction = null;
        Session currentSession = null;
        try {
            HibernateUtilWorking util = new HibernateUtilWorking();

            SessionFactory sessionFactory = util.creteSessionFactory();

            currentSession = sessionFactory.openSession();

            transaction = currentSession.beginTransaction();

            Employee employee=currentSession.get(Employee.class, "232649");

            System.out.println(employee);

            employee.setLastName("Mukherjee");

            //currentSession.saveOrUpdate(employee);

            System.out.println(employee);

            currentSession.detach(employee);

            employee.setLastName("Mondal");

            //currentSession.saveOrUpdate(employee);

            transaction.commit();

        } catch (Exception ex) {

            if (transaction != null) {
                transaction.rollback();
            }

            if (currentSession != null) {
                currentSession.close();
            }

            ex.printStackTrace();
        }
    }
}
