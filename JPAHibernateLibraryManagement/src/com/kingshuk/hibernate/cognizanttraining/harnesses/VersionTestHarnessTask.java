package com.kingshuk.hibernate.cognizanttraining.harnesses;

import com.kingshuk.hibernate.cognizanttraining.modelrevised3.BookInventory;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.BookIssue;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class VersionTestHarnessTask implements Runnable {
    private SessionFactory factory;

    public VersionTestHarnessTask(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */


    @Override
    public void run() {
        Transaction transaction1 = null;
        try {
            Session session1=factory.openSession();

            transaction1 = session1.beginTransaction();

            BookInventory book = session1.get(BookInventory.class, 15);

            Customer customer = session1.get(Customer.class, "rishi1626");

            Date date = new Date();

            Date dueDate = new Date(date.getTime() + 3);

            BookIssue bookIssue = new BookIssue();
            bookIssue.setBook(book);
            bookIssue.setCustomer(customer);
            bookIssue.setIssueDate(date);
            bookIssue.setDueDate(dueDate);

            book.setStock(book.getStock() - 1);

            session1.persist(bookIssue);


            transaction1.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction1 != null) {
                transaction1.rollback();
            }
        }
    }
}
