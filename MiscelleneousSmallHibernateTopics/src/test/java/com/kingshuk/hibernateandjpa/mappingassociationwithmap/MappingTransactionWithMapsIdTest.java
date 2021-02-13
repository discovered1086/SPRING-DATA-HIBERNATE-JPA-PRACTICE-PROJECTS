package com.kingshuk.hibernateandjpa.mappingassociationwithmap;

import com.kingshuk.hibernateandjpa.mappingwithmapsidannotation.experiment2.TransactionCategoryEntity;
import com.kingshuk.hibernateandjpa.mappingwithmapsidannotation.experiment2.TransactionEntity;
import com.kingshuk.hibernateandjpa.utility.ConfigurationUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.Assert.assertNotNull;

/**
 * This is a test class to check how hibernate validation works
 *
 * @author kingshuksmacbookpro
 */
@RunWith(SpringRunner.class)
public class MappingTransactionWithMapsIdTest {

    private SessionFactory sessionFactory;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        Configuration configuration = ConfigurationUtil.getConfiguration();

        if (configuration != null) {
            configuration.addAnnotatedClass(TransactionEntity.class);
            configuration.addAnnotatedClass(TransactionCategoryEntity.class);

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
        EntityManager entityManager = sessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        final TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionDescription("Purchasing wallet from ebay")
                .transactionAmount(BigDecimal.valueOf(20.99))
                .transactionDate(OffsetDateTime.now())
                .transactionNotes("Online purchase")
                .transactionAccountId("ACCT547564564")
                .build();

		final TransactionCategoryEntity categoryEntity = TransactionCategoryEntity.builder()
				.transactionCategory("CTGRY15465")
				.transactionSubCategory("SBCTRGY1654654")
				.parentTransaction(transactionEntity)
				.build();

		transactionEntity.setTransactionCategory(categoryEntity);

		entityManager.persist(transactionEntity);

		entityManager.getTransaction().commit();

        entityManager.close();
    }

}
