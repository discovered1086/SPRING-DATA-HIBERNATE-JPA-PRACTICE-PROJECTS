package com.kingshuk.hibernate.cognizanttraining.harnesses;

import com.kingshuk.hibernate.cognizanttraining.commonutil.CognizantHibernateUtilUpdated;
import com.kingshuk.hibernate.cognizanttraining.commonutil.HibernateUtil;
import com.kingshuk.hibernate.cognizanttraining.modelsrevised2.Address;
import com.kingshuk.hibernate.cognizanttraining.modelsrevised2.BookInventory;
import com.kingshuk.hibernate.cognizanttraining.modelsrevised2.BookIssue;
import com.kingshuk.hibernate.cognizanttraining.modelsrevised2.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RevisedLibraryManagementHarness {

    public static void main(String[] args) {
        Transaction transaction = null;
        HibernateUtil hibernateUtil =new CognizantHibernateUtilUpdated();

        SessionFactory factory= hibernateUtil.createSessionFactory();
        try (Session session = factory.openSession()) {
            //Step 1 is to obtain a session factory from the hibernate util class

            //Step 2: Getting the hibernate session from the Factory

            //Step 3: Starting the transaction
            transaction = session.beginTransaction();



            /*BookInventory inventory=new BookInventory();
            inventory.setBookTitle("Head First EJB");
            inventory.setAuthor("Kathy Sierra");
            inventory.setPrice(25.00);
            inventory.setStock(20);*/

            /*Address address = new Address();
            address.setCity("Hartford");
            address.setState("CT");
            address.setZipCode("06105");*/
            Address address=session.get(Address.class, 13);

            BookInventory inventory=session.get(BookInventory.class, 11);

            /*Query query = session.createNativeQuery("SELECT CUSTOMER_SEQ.NEXTVAL FROM DUAL");
            BigDecimal seq = (BigDecimal) query.list().get(0);

            Customer customer = new Customer();
            customer.setAddress(address);
            customer.setEmailAddress("ar.deeksha.b@gmail.com");
            customer.setFirstName("Deeksha");
            customer.setLastName("Banerjee");
            customer.setUserName("rishi2626");
            customer.setCustomerId(seq.intValue());*/
            Customer customer=session.get(Customer.class, "rishi2626");

            System.out.println(customer);

            BookIssue bookIssue=new BookIssue();
            bookIssue.setBook(inventory);
            bookIssue.setCustomer(customer);
            bookIssue.setIssueDate(new Date());

            /*List<BookIssue> bookIssues = new ArrayList<>();
            bookIssues.add(bookIssue);
            customer.setIssuedBooks(bookIssues);

            //session.persist(inventory);
            session.persist(customer);*/
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
