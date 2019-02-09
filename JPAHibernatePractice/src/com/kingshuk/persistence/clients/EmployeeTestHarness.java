package com.kingshuk.persistence.clients;

import com.kingshuk.persistence.configuration.HibernateUtilWorking;
import com.kingshuk.persistence.studentmanagement.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeTestHarness {

    public static void main(String[] args) {
        Transaction transaction = null;
        Session currentSession = null;
        try {
            HibernateUtilWorking util = new HibernateUtilWorking();

            SessionFactory sessionFactory = util.creteSessionFactory();

            currentSession = sessionFactory.openSession();

            transaction = currentSession.beginTransaction();

            Employee employee = new Employee();
            employee.setEmployeeId("232649");
            employee.setFirstName("Kingshuk");
            employee.setLastName("Banerjee");
            try {
                employee.setHireDate(new SimpleDateFormat("MM/dd/yyyy").parse("03/29/2010"));
                //employee.setTerminationDate(new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2100"));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            currentSession.saveOrUpdate(employee);

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
