package com.kingshuk.hibernateandjpa.mappingwithenumandembeddable;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingshuk.hibernateandjpa.config.ConfigurationUtil;


public class MappingWithEnumAndEmbeddableRunner {

	public static void main(String[] args) {
		Transaction transaction = null;
		
		try (SessionFactory sessionFactory = ConfigurationUtil.getConfiguration()
											 .addAnnotatedClass(Author.class)
											 .addAnnotatedClass(Address.class)
											 .buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			
			transaction = session.beginTransaction();
			
			//Load the object to memory from the json file
			ObjectMapper objectMapper = new ObjectMapper();
			
			Author author = objectMapper.readValue(
					new File("jsonfiles/AuthorAddressInitial.json"), Author.class);
			
			Long authorId = (Long)session.save(author);
			
					
			author = session.get(Author.class, authorId);
			
			System.out.println(author);
			
			
			transaction.commit();
		}catch (Exception e) {
			if (!Objects.isNull(transaction)) {
				transaction.rollback();
			}
		}
	}

}
