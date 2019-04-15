package com.kingshuk.hibernateandjpa.hibernatebeanvalidation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.kingshuk.hibernateandjpa.hibernatebeanvalidation.model.Account;
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
public class HibernateValidatorTests {

	private SessionFactory sessionFactory;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		Configuration configuration = ConfigurationUtil.getConfiguration();

		if(configuration != null) {
			configuration.addAnnotatedClass(Account.class);
			
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
	public void testConstraintViolationWithBlankAccountNumber() {
		log.info("Starting bean validation......");

		EntityManager entityManager = sessionFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Account account = Account.builder().accountDesc("HDFC Bank").accountName("HDFC Account").accountNumber(null)
				.build();

		entityManager.persist(account);

		exception.expect(ConstraintViolationException.class);

		entityManager.getTransaction().commit();

		entityManager.close();
	}
	
	
	@Test
	public void testConstraintViolationWithLargerAccountNumber() {
		log.info("Starting bean validation......");

		EntityManager entityManager = sessionFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Account account = Account.builder().accountDesc("HDFC Bank").accountName("HDFC Account")
				.accountNumber("564546545645645645645646545645456456")
				.build();

		entityManager.persist(account);

		exception.expect(ConstraintViolationException.class);

		entityManager.getTransaction().commit();

		entityManager.close();
	}

	@Test
	public void testConstraintViolationWithOutExpectedExceptionClass() {
		log.info("Starting bean validation......");

		EntityManager entityManager = sessionFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Account account = Account.builder().accountDesc("HDFC Bank").accountName("HDFC Account").accountNumber(null)
				.build();

		try {
			entityManager.persist(account);

			entityManager.getTransaction().commit();

			fail("ConstraintViolationException expected");
		} catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

			constraintViolations.forEach(cv -> log.info(cv.toString()));
		} catch (Exception e) {

		}

		entityManager.close();
	}

}
