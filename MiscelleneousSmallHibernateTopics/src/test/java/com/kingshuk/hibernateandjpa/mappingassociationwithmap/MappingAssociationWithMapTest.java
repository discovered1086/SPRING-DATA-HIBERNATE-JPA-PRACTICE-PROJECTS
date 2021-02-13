package com.kingshuk.hibernateandjpa.mappingassociationwithmap;

import com.kingshuk.hibernateandjpa.utility.ConfigurationUtil;
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
import javax.persistence.TypedQuery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This is a test class to check how hibernate validation works
 *
 * @author kingshuksmacbookpro
 */
@RunWith(SpringRunner.class)
public class MappingAssociationWithMapTest {

    private SessionFactory sessionFactory;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        Configuration configuration = ConfigurationUtil.getConfiguration();

        if (configuration != null) {
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
        EntityManager entityManager = sessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Author> q = entityManager
                .createQuery("SELECT a FROM Author a JOIN a.books b WHERE b.title LIKE :title", Author.class);
        q.setParameter("title", "%Hibernate Tips%");

        Author author = q.getSingleResult();

        assertNotNull(author);

        assertEquals(3, author.getId());

        entityManager.getTransaction().commit();

        entityManager.close();
    }

}
