package com.kingshuk.hibernateandjpa.withoutmappedattribute;

import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.config.ConfigurationUtil;



public class MappingAssociationWithMapRunner {

	public static void main(String[] args) throws Exception {

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.getConfiguration()
											 .addAnnotatedClass(Author.class)
											 .addAnnotatedClass(Book.class)
											 .buildSessionFactory();
				Session session = sessionFactory.openSession()) {

			transaction = session.beginTransaction();
			
			Author a = new Author();
			a.setFirstName("Thorben");
			a.setLastName("Janssen");

			session.save(a);

			Book b = new Book();
			b.setTitle("Hibernate Tips");
			// b.setFormat(Format.PAPERBACK);
			b.setSequenceNumber(1);
			b.getAuthors().add(a);

			session.save(b);

			Book b1 = new Book();
			b1.setTitle("Hibernate Books");
			// b.setFormat(Format.PAPERBACK);
			b1.setSequenceNumber(2);
			b1.getAuthors().add(a);

			session.save(b1);

			a.getBooks().put(b.getSequenceNumber(), b);
			a.getBooks().put(b1.getSequenceNumber(), b1);
			
			transaction.commit();
		} catch (Exception e) {
			if (!Objects.isNull(transaction)) {
				transaction.rollback();
			}
		}
	}

}
