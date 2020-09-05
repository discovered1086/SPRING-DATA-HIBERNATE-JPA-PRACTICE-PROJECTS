package com.hibernatepractice.harness.advancedmapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.inheritance.mappedsuperclass.BondEntity;
import com.hibernatepractice.model.inheritance.mappedsuperclass.StockEntity;

public class MappedSuperClassHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			StockEntity stockEntity = StockEntity.builder().issuer("Goldman Sachs")
					.purchaseDate(LocalDate.now().minus(3, ChronoUnit.DAYS)).quantity(150)
					.investmentName("For marriage")
					.sharePrice(BigDecimal.valueOf(79.20)).build();

			BondEntity bondEntity = BondEntity.builder().issuer("Goldman Sachs")
					.purchaseDate(LocalDate.now().minus(3, ChronoUnit.DAYS)).interestRate(20.20)
					.investmentName("For education")
					.value(BigDecimal.valueOf(1000.00)).maturityDate(LocalDate.now().plus(Period.ofYears(3))).build();

			session.save(stockEntity);

			session.save(bondEntity);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
