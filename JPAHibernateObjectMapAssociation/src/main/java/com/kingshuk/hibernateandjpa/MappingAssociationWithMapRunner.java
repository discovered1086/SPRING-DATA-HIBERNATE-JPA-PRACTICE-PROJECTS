package com.kingshuk.hibernateandjpa;

import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.config.ConfigurationUtil;

public class MappingAssociationWithMapRunner {

	public static void main(String[] args) throws Exception {

		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.getConfiguration().addAnnotatedClass(Author.class)
				.addAnnotatedClass(Book.class).buildSessionFactory(); Session session = sessionFactory.openSession()) {

			transaction = session.beginTransaction();
			Author a = new Author();
			a.setFirstName("Thorben");
			a.setLastName("Janssen");

			session.save(a);

			Book b = new Book();
			b.setTitle("Hibernate Tips");
			// b.setFormat(Format.PAPERBACK);
			b.getAuthors().add(a);

			session.save(b);

			a.getBooks().put(b.getTitle(), b);
			
			transaction.commit();

		} catch (Exception e) {
			if (!Objects.isNull(transaction)) {
				transaction.rollback();
			}
		}

	}

}
