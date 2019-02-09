package com.kingshuk.hibernate.cognizanttraining.harnesses;

import com.kingshuk.hibernate.cognizanttraining.commonutil.CognizantHibernateUtilUpdated;
import com.kingshuk.hibernate.cognizanttraining.commonutil.HibernateUtil;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.Address;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.BookInventory;
import com.kingshuk.hibernate.cognizanttraining.modelrevised3.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class RevisedLibraryWithVersionHarness {

    public static void main(String[] args) {
        Transaction transaction = null;
        HibernateUtil hibernateUtil =new CognizantHibernateUtilUpdated();

        SessionFactory factory= hibernateUtil.createSessionFactory();
        try (Session session = factory.openSession()) {

            transaction=session.beginTransaction();



            BookInventory inventory=new BookInventory();
            inventory.setBookId(1);
            inventory.setBookTitle("Head First EJB");
            inventory.setAuthor("Kathy Sierra");
            inventory.setPrice(25.00);
            inventory.setStock(1);

            Address address = new Address();
            address.setAddressLine1("600 Asylum Ave");
            address.setAddressLine2("Apt - 316");
            address.setCity("Hartford");
            address.setState("CT");
            address.setZipCode("06105");

            BigDecimal customerSeq= (BigDecimal) session.createNativeQuery("SELECT CUSTOMER_SEQ.NEXTVAL FROM DUAL").getSingleResult();

            Customer customer=new Customer(customerSeq.intValue());
            customer.setFirstName("Deeksha");
            customer.setLastName("Banerjee");
            customer.setEmailAddress("ar.deeksha.b@gmail.com");
            customer.setAddress(address);
            customer.setUserName("rishi1626");



            session.persist(inventory);
            session.persist(customer);
            //session.persist(bookIssue);

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
