package com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingwhere;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kingshuk.hibernateandjpa.softdeleteandfiltering.configuration.ConfigurationUtil;
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingwhere.model.TransactionTypeEnum;
import com.kingshuk.hibernateandjpa.softdeleteandfiltering.filtering.usingwhere.model.WhereCategoryEntity;

public class WherePracticeHarnessWithId {

	public static void main(String[] args) {
		Transaction transaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();

				Session session = sessionFactory.openSession();) {

			transaction = session.beginTransaction();

			OffsetDateTime effectiveDate = OffsetDateTime.parse("2019-11-25T00:00:00+05:00");

			WhereCategoryEntity categoryEntity = WhereCategoryEntity.builder()
					.categoryDescription("Bill Payment for various types of bills").categoryName("Bill Payment")
					.categoryEffectiveDate(effectiveDate).categoryTransactionType(TransactionTypeEnum.EXPENSE).build();

			WhereCategoryEntity categoryEntity2 = WhereCategoryEntity.builder()
					.categoryDescription("Bill Payment for loans").categoryName("Loan Payment")
					.categoryEffectiveDate(effectiveDate).categoryTransactionType(TransactionTypeEnum.EXPENSE).build();

			session.save(categoryEntity);
			session.save(categoryEntity2);

			transaction.commit();

			transaction = session.beginTransaction();

			TypedQuery<WhereCategoryEntity> allCategoryQuery = session.createNamedQuery("findCategoryByWhereName",
					WhereCategoryEntity.class);

			allCategoryQuery.setParameter("categoryNameInput", "%Bill%");

			WhereCategoryEntity newCategoryEntity = allCategoryQuery.getSingleResult();

			OffsetDateTime currentDateTime = OffsetDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneOffset.of("-5"))
					.truncatedTo(ChronoUnit.SECONDS);

			newCategoryEntity.setCategoryTerminationDate(currentDateTime);

			session.saveOrUpdate(newCategoryEntity);
			
			transaction.commit();

			transaction = session.beginTransaction();

			System.out.println("\n___Please enter the category id you would like to check_____");
			
			Scanner scanner = new Scanner(System.in);
			
			long id = scanner.nextLong();
			
			scanner.close();

			WhereCategoryEntity categoryEntity3 = session.get(WhereCategoryEntity.class, id);
			
			System.out.println(categoryEntity3);

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
