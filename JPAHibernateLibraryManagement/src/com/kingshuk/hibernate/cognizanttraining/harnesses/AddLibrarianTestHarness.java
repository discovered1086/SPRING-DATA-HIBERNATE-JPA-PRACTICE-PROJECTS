package com.kingshuk.hibernate.cognizanttraining.harnesses;

import com.kingshuk.hibernate.cognizanttraining.commonutil.CognizantHibernateUtilUpdated;
import com.kingshuk.hibernate.cognizanttraining.commonutil.HibernateUtil;
import com.kingshuk.hibernate.cognizanttraining.modelsrevised.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.print.Book;

public class AddLibrarianTestHarness {

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



            Address address=new Address();
            address.setCity("Hartford");
            address.setState("CT");
            address.setZipCode("06105");
            address.setAddressLine1("600 Asylum Avenue");
            address.setAddressLine2("Apt - 316");
            address.setAddressId(124);

            Librarian customer=new Librarian();
            customer.setAddress(address);
            customer.setEmailAddress("sly.mania42@gmail.com");
            customer.setFirstName("Kingshuk");
            customer.setLastName("Mukherjee");
            customer.setUserName("rishi2626");

            BookInventory issuedBook=session.get(BookInventory.class,1);

            issuedBook.setStock(issuedBook.getStock()-2);

            /*BookCustomerCompositeKey compositeKey=new BookCustomerCompositeKey();
            compositeKey.setBookId(issuedBook.getBookId());
            compositeKey.setCustomerId(customer.getCustomerId());

            BookIssue bookIssue=new BookIssue();
            bookIssue.setCompositeKey(compositeKey);
            bookIssue.setIssueDate("01/22/2018");

            session.persist(customer);
            session.persist(bookIssue);
            session.saveOrUpdate(issuedBook);*/

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
