package jpahibernate.clients;

import jpahibernate.model.Department;
import jpahibernate.model.FeatherWeightUFCFighter;
import jpahibernate.model.HeavyWeightUFCFighter;
import jpahibernate.model.UFCFighter;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by kings on 04-Mar-17.
 */
public class InheritanceMappingClient {

    private static Logger logger = Logger.getLogger(InheritanceMappingClient.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityManager entityManager2 = null;
        EntityTransaction transaction = null;
        EntityTransaction transaction2 = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JPAProject");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            /* Testing the Joined inheritance strategy
            FeatherWeightUFCFighter featherWeightUFCFighter = new FeatherWeightUFCFighter();
            featherWeightUFCFighter.setName("Anderson Silva");
            featherWeightUFCFighter.setAge(41);
            featherWeightUFCFighter.setHailsFrom("Brazil");
            featherWeightUFCFighter.setChampionOfTheDivision("Tyron WoodLey");

            HeavyWeightUFCFighter heavyWeightUFCFighter = new HeavyWeightUFCFighter();
            heavyWeightUFCFighter.setName("Alistair Overreem");
            heavyWeightUFCFighter.setAge(34);
            heavyWeightUFCFighter.setHailsFrom("Netherlands");
            heavyWeightUFCFighter.setLegendOfThisDivision("Brock Lesner");

            entityManager.persist(featherWeightUFCFighter);
            entityManager.persist(heavyWeightUFCFighter);*/

            //Polymorphic and derived class query for the single table strategy

            /*List<UFCFighter> ufcFighterList = entityManager.createQuery("select ufcfighter from UFCFighter  ufcfighter").getResultList();
            for (UFCFighter ufcFighter : ufcFighterList) {
                logger.info("The name is " + ufcFighter.getName());
                logger.info("The fighter hails from  " + ufcFighter.getHailsFrom());
                logger.info("The weight of the fighter is " + ufcFighter.getWeight());
            }

            List<FeatherWeightUFCFighter> ufcFighterList = entityManager.createQuery("select featherWeightfighter from FeatherWeightUFCFighter  featherWeightfighter").getResultList();
            for (FeatherWeightUFCFighter ufcFighter : ufcFighterList) {
                logger.info("The name is " + ufcFighter.getName());
                logger.info("The fighter hails from  " + ufcFighter.getHailsFrom());
                logger.info("The weight of the fighter is " + ufcFighter.getWeight());
            }*/

            //Polymorphic and derived class query for the  joined inheritance strategy

            /*List<UFCFighter> ufcFighterList = entityManager.createQuery("select ufcfighter from UFCFighter  ufcfighter").getResultList();
            for (UFCFighter ufcFighter : ufcFighterList) {
                logger.info("The name is " + ufcFighter.getName());
                logger.info("The fighter hails from  " + ufcFighter.getHailsFrom());
                logger.info("The weight of the fighter is " + ufcFighter.getWeight());
            }*/

            /*List<FeatherWeightUFCFighter> ufcFighterList = entityManager.createQuery("select featherWeightfighter from FeatherWeightUFCFighter  featherWeightfighter").getResultList();
            for (FeatherWeightUFCFighter ufcFighter : ufcFighterList) {
                logger.info("The name is " + ufcFighter.getName());
                logger.info("The fighter hails from  " + ufcFighter.getHailsFrom());
                logger.info("The weight of the fighter is " + ufcFighter.getWeight());
            }*/

            /*Testing the table per class inheritance strategy
            FeatherWeightUFCFighter featherWeightUFCFighter = new FeatherWeightUFCFighter();
            featherWeightUFCFighter.setName("Anderson Silva");
            featherWeightUFCFighter.setAge(41);
            featherWeightUFCFighter.setHailsFrom("Brazil");
            featherWeightUFCFighter.setChampionOfTheDivision("Tyron WoodLey");

            FeatherWeightUFCFighter featherWeightUFCFighter2 = new FeatherWeightUFCFighter();
            featherWeightUFCFighter2.setName("Micheal Bisping");
            featherWeightUFCFighter2.setAge(34);
            featherWeightUFCFighter2.setHailsFrom("England");
            featherWeightUFCFighter2.setChampionOfTheDivision("Tyron WoodLey");

            HeavyWeightUFCFighter heavyWeightUFCFighter = new HeavyWeightUFCFighter();
            heavyWeightUFCFighter.setName("Alistair Overreem");
            heavyWeightUFCFighter.setAge(34);
            heavyWeightUFCFighter.setHailsFrom("Netherlands");
            heavyWeightUFCFighter.setLegendOfThisDivision("Brock Lesner");

            HeavyWeightUFCFighter heavyWeightUFCFighter2 = new HeavyWeightUFCFighter();
            heavyWeightUFCFighter2.setName("Mark Hunt");
            heavyWeightUFCFighter2.setAge(36);
            heavyWeightUFCFighter2.setHailsFrom("USA");
            heavyWeightUFCFighter2.setLegendOfThisDivision("Brock Lesner");

            entityManager.persist(featherWeightUFCFighter);
            entityManager.persist(heavyWeightUFCFighter);

            entityManager.persist(featherWeightUFCFighter2);
            entityManager.persist(heavyWeightUFCFighter2);*/

            //Polymorphic and derived class query for the table per class strategy

            /*List<UFCFighter> ufcFighterList = entityManager.createQuery("select ufcfighter from UFCFighter  ufcfighter").getResultList();
            for (UFCFighter ufcFighter : ufcFighterList) {
                logger.info("The name is " + ufcFighter.getName());
                logger.info("The fighter hails from  " + ufcFighter.getHailsFrom());
                logger.info("The weight of the fighter is " + ufcFighter.getWeight());
            }*/

            List<FeatherWeightUFCFighter> ufcFighterList = entityManager.createQuery("select featherWeightfighter from FeatherWeightUFCFighter  featherWeightfighter").getResultList();
            for (FeatherWeightUFCFighter ufcFighter : ufcFighterList) {
                logger.info("The name is " + ufcFighter.getName());
                logger.info("The fighter hails from  " + ufcFighter.getHailsFrom());
                logger.info("The weight of the fighter is " + ufcFighter.getWeight());
            }


            transaction.commit();
            //entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
}
