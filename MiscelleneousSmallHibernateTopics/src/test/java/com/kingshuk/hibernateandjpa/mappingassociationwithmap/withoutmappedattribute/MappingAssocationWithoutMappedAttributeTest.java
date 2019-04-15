package com.kingshuk.hibernateandjpa.mappingassociationwithmap.withoutmappedattribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.kingshuk.hibernateandjpa.utility.ConfigurationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a test class to check how hibernate validation works
 * 
 * @author kingshuksmacbookpro
 *
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class MappingAssocationWithoutMappedAttributeTest {

	private SessionFactory sessionFactory;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		Configuration configuration = ConfigurationUtil.getConfiguration();

		if(configuration != null) {
			configuration.addAnnotatedClass(Book.class);
			configuration.addAnnotatedClass(Author.class);
			
			sessionFactory = configuration.buildSessionFactory();
		}
	}

	@After
	public void closeEntityManagerFactory() {
		sessionFactory.close();
	}

	@Test
	public void checkSessionFactory() {
		assertNotNull(sessionFactory);
	}

	@Test
	public void testAuthorBookWithTypedQuery() {
		log.info("Starting bean validation......");

		EntityManager entityManager = sessionFactory.createEntityManager();

		entityManager.getTransaction().begin();

		/*TypedQuery<Author> q = entityManager
				.createQuery("SELECT a FROM Author a JOIN a.books b where a.id = :id", Author.class);
		q.setParameter("id", 33L);
		
		Author author = q.getSingleResult();*/
		Author author = entityManager.find(Author.class, 33L);
		
		assertNotNull(author);
		
		assertEquals(2, author.getBooks().size());
		
		assertEquals("Hibernate Tips", author.getBooks().get(1).getTitle());
		assertEquals("Hibernate Books", author.getBooks().get(2).getTitle());

		entityManager.getTransaction().commit();

		entityManager.close();
	}

}
