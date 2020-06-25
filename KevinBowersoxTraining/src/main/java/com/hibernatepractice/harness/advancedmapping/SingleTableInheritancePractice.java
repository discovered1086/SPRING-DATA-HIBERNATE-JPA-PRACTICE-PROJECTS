package com.hibernatepractice.harness.advancedmapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.inheritance.singletable.BondStEntity;
import com.hibernatepractice.model.inheritance.singletable.PortfolioStEntity;
import com.hibernatepractice.model.inheritance.singletable.StockStEntity;

public class SingleTableInheritancePractice {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			PortfolioStEntity portfolioEntity = PortfolioStEntity.builder().name("Kingshuk's investments").build();

			StockStEntity stockEntity = StockStEntity.builder().issuer("Goldman Sachs")
					.purchaseDate(LocalDate.now().minus(3, ChronoUnit.DAYS)).quantity(150)
					.investmentName("For marriage").portfolio(portfolioEntity).sharePrice(BigDecimal.valueOf(79.20))
					.build();

			BondStEntity bondEntity = BondStEntity.builder().issuer("Goldman Sachs")
					.purchaseDate(LocalDate.now().minus(3, ChronoUnit.DAYS)).interestRate(20.20)
					.investmentName("For education").portfolio(portfolioEntity).value(BigDecimal.valueOf(1000.00))
					.maturityDate(LocalDate.now().plus(Period.ofYears(3))).build();

			portfolioEntity.setInvestements(Arrays.asList(stockEntity, bondEntity));

			session.save(portfolioEntity);

			session.getTransaction().commit();

			PortfolioStEntity savedPortfolio = session.get(PortfolioStEntity.class, portfolioEntity.getPortfolioId());

			savedPortfolio.getInvestements().forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
