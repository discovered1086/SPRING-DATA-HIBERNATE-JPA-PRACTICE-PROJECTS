package com.kingshuk.hibernate.cognizanttraining.harnesses;


import com.kingshuk.hibernate.cognizanttraining.commonutil.CognizantHibernateUtilUpdated;
import com.kingshuk.hibernate.cognizanttraining.commonutil.HibernateUtil;
import com.kingshuk.hibernate.cognizanttraining.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LibraryManagementSystemHarness {

    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        HibernateUtil hibernateUtil=null;

        try{
            hibernateUtil=new CognizantHibernateUtilUpdated();
            //Step 1 is to obtain a session factory from the hibernate util class
            SessionFactory factory= hibernateUtil.createSessionFactory();

            //Step 2: Getting the hibernate session from the Factory
            session=factory.openSession();

            //Step 3: Starting the transaction
            transaction=session.beginTransaction();



            BookInventory inventory=new BookInventory();
            inventory.setBookId(1);
            inventory.setBookTitle("Head First EJB");
            inventory.setAuthor("Kathy Sierra");
            inventory.setPrice(25.00);
            inventory.setStock(20);

            CustomerAddress address=new CustomerAddress();
            address.setCity("Hartford");
            address.setState("CT");
            address.setZipCode(06105);

            Customer customer=new Customer();
            customer.setCustomerId(1);
            customer.setCustomerName("Kingshuk Mukherjee");
            customer.setAddress(address);

            BookCustomerCompositeKey compositeKey=new BookCustomerCompositeKey();
            compositeKey.setBookId(inventory.getBookId());
            compositeKey.setCustomerId(customer.getCustomerId());

            BookIssue bookIssue=new BookIssue();
            bookIssue.setCompositeKey(compositeKey);
            bookIssue.setIssueDate("01/22/2018");
            bookIssue.setReturnDate("01/30/2018");

            session.persist(inventory);
            session.persist(customer);
            session.persist(bookIssue);

            transaction.commit();

        }catch (Exception ex){
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session != null) {
                session.close();
            }
        }

    }
}
