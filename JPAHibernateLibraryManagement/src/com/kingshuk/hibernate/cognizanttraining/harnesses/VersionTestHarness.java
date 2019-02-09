package com.kingshuk.hibernate.cognizanttraining.harnesses;

import com.kingshuk.hibernate.cognizanttraining.commonutil.CognizantHibernateUtilUpdated;
import com.kingshuk.hibernate.cognizanttraining.commonutil.HibernateUtil;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.BookInventory;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.BookIssue;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class VersionTestHarness {

    public static void main(String[] args) {
        Transaction transaction = null;
        HibernateUtil hibernateUtil = new CognizantHibernateUtilUpdated();

        SessionFactory factory = hibernateUtil.createSessionFactory();
        try (Session session = factory.openSession()) {

            Thread newIssue = new Thread(new VersionTestHarnessTask(factory));

            newIssue.start();


            transaction = session.beginTransaction();

            BookInventory book = session.get(BookInventory.class, 15);

            Customer customer = session.get(Customer.class, "rishi2616");

            Date date = new Date();

            Date dueDate = new Date(date.getTime() + 3);

            BookIssue bookIssue = new BookIssue();
            bookIssue.setBook(book);
            bookIssue.setCustomer(customer);
            bookIssue.setIssueDate(date);
            bookIssue.setDueDate(dueDate);

            Thread.sleep(3000);

            book.setStock(book.getStock() - 1);


            session.persist(bookIssue);

            transaction.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
