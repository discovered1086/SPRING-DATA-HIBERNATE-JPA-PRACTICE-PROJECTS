package com.kingshuk.hibernateandjpa.softdelete;

import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kingshuk.hibernateandjpa.hibernatesoftdelete.model.Account;
import com.kingshuk.hibernateandjpa.utility.ConfigurationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a test class to prove two things
 * 
 * 1) How soft delete works
 * 2) It calls the @PreRemove method before deleting the record
 * (By deleting I mean executing the query we have in the @SQLDelete annotation
 * 
 * @author kingshuksmacbookpro
 *
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class HibernateSoftDeleteValidationTests {

	@Autowired
	private SessionFactory sessionFactory;

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
	public void testSoftDelete() {
		log.info("Starting soft delete test......");

		EntityManager entityManager = sessionFactory.createEntityManager();

		entityManager.getTransaction().begin();

		TypedQuery<Account> allAccountsQuery = entityManager.createNamedQuery("Account.FindByAccountName",
				Account.class);

		allAccountsQuery.setParameter("accountName", "%HDFC%");

		List<Account> resultList = allAccountsQuery.getResultList();

		Account account = null;

		if (!resultList.isEmpty()) {
			account = resultList.get(0);

			log.info(String.format("The account name - %s and account state - %s", account.getAccountName(),
					account.getAccountState()));

			// now call remove
			entityManager.remove(account);
			entityManager.flush();

			log.info(String.format("The account name - %s and account state - %s", account.getAccountName(),
					account.getAccountState()));

			entityManager.getTransaction().commit();
			entityManager.close();

		}

		entityManager = sessionFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		account = entityManager.find(Account.class, account.getAccountId());
		
		assertNull(account);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}

}
