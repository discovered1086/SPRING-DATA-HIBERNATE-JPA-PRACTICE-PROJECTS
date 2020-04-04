package com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingloader.harness;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.softdeleteandfiltering.configuration.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingloader.model.LoaderCategoryEntity;
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingloader.model.TransactionTypeEnum;

public class LoaderPracticeHarness {

	public static void main(String[] args) {
		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			OffsetDateTime effectiveDate = OffsetDateTime.parse("2019-11-25T00:00:00+05:00");

			LoaderCategoryEntity categoryEntity = LoaderCategoryEntity.builder()
					.categoryDescription("Bill Payment for various types of bills").categoryName("Bill Payment")
					.categoryEffectiveDate(effectiveDate).categoryTransactionType(TransactionTypeEnum.EXPENSE).build();

			LoaderCategoryEntity categoryEntity2 = LoaderCategoryEntity.builder()
					.categoryDescription("Bill Payment for loans").categoryName("Loan Payment")
					.categoryEffectiveDate(effectiveDate).categoryTransactionType(TransactionTypeEnum.EXPENSE).build();

			session.save(categoryEntity);
			session.save(categoryEntity2);

			transaction.commit();

			transaction = session.beginTransaction();

			TypedQuery<LoaderCategoryEntity> allCategoryQuery = session.createNamedQuery("findCategoryByName",
					LoaderCategoryEntity.class);

			allCategoryQuery.setParameter("categoryNameInput", "%Bill%");

			LoaderCategoryEntity newCategoryEntity = allCategoryQuery.getSingleResult();

			OffsetDateTime currentDateTime = OffsetDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneOffset.of("-5"))
					.truncatedTo(ChronoUnit.SECONDS);

			newCategoryEntity.setCategoryTerminationDate(currentDateTime);

			session.delete(newCategoryEntity);

			transaction.commit();

			transaction = session.beginTransaction();

			TypedQuery<LoaderCategoryEntity> allCategoryQuery2 = session.createQuery("from LoaderCategory c",
					LoaderCategoryEntity.class);

			System.out.println("\n*************\n Printing all active categories\n");

			List<LoaderCategoryEntity> resultList = allCategoryQuery2.getResultList();

			resultList.forEach(System.out::println);

			transaction.commit();

			session.close();

		} catch (Exception exception) {
			exception.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
