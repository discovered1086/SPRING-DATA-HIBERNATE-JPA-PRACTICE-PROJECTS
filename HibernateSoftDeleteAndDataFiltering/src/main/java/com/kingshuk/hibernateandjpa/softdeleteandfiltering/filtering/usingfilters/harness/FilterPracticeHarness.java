package com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingfilters.harness;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingfilters.model.CategoryEntity;
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingfilters.model.TransactionTypeEnum;

public class FilterPracticeHarness {

	public static void main(String[] args) {
		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			OffsetDateTime effectiveDate = OffsetDateTime.parse("2019-11-25T00:00:00+05:00");

			CategoryEntity categoryEntity = CategoryEntity.builder()
					.categoryDescription("Bill Payment for various types of bills").categoryName("Bill Payment")
					.categoryEffectiveDate(effectiveDate).categoryTransactionType(TransactionTypeEnum.EXPENSE).build();

			CategoryEntity categoryEntity2 = CategoryEntity.builder().categoryDescription("Bill Payment for loans")
					.categoryName("Loan Payment").categoryEffectiveDate(effectiveDate)
					.categoryTransactionType(TransactionTypeEnum.EXPENSE).build();

			session.save(categoryEntity);
			session.save(categoryEntity2);

			transaction.commit();

			transaction = session.beginTransaction();

			TypedQuery<CategoryEntity> allCategoryQuery = session.createNamedQuery("findExistingCategoryByName",
					CategoryEntity.class);

			allCategoryQuery.setParameter("categoryNameInput", "%Bill%");

			CategoryEntity newCategoryEntity = allCategoryQuery.getSingleResult();

			OffsetDateTime currentDateTime = OffsetDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneOffset.of("-5"))
					.truncatedTo(ChronoUnit.SECONDS);

			newCategoryEntity.setCategoryTerminationDate(currentDateTime);

			session.saveOrUpdate(newCategoryEntity);

			transaction.commit();

			transaction = session.beginTransaction();

			setFiltersOnSession(session);

			TypedQuery<CategoryEntity> allCategoryQuery2 = session.createQuery("from Category c",
					CategoryEntity.class);

			System.out.println("\n*************\n Printing all active categories\n");

			List<CategoryEntity> resultList = allCategoryQuery2.getResultList();
			
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

	private static void setFiltersOnSession(Session session) {
		OffsetDateTime timeNow = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("-5"))
				.truncatedTo(ChronoUnit.SECONDS);
		session.enableFilter("effTermDateCheck").setParameter("effDate", timeNow)
		.setParameter("termDate", timeNow);
	}

}
