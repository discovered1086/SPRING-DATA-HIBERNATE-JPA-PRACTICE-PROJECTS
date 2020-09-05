package com.hibernatepractice.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.model.BudgetEntity;
import com.hibernatepractice.model.ComplexUser;
import com.hibernatepractice.model.Credential;
import com.hibernatepractice.model.CurrencyEntity;
import com.hibernatepractice.model.FinancialAccountEntity;
import com.hibernatepractice.model.TemporalTestEntity;
import com.hibernatepractice.model.TransactionEntity;
import com.hibernatepractice.model.User;
import com.hibernatepractice.model.inheritance.mappedsuperclass.BondEntity;
import com.hibernatepractice.model.inheritance.mappedsuperclass.StockEntity;
import com.hibernatepractice.model.inheritance.singletable.BondStEntity;
import com.hibernatepractice.model.inheritance.singletable.InvestmentStEntity;
import com.hibernatepractice.model.inheritance.singletable.PortfolioStEntity;
import com.hibernatepractice.model.inheritance.singletable.StockStEntity;
import com.hibernatepractice.model.inheritance.tableperclass.BondTpcEntity;
import com.hibernatepractice.model.inheritance.tableperclass.PortfolioTpcEntity;
import com.hibernatepractice.model.inheritance.tableperclass.StockTpcEntity;

public class HibernateConfigUtil {

	private HibernateConfigUtil() {

	}

	// Singleton implementation of session factory
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(User.class)
			.addAnnotatedClass(TemporalTestEntity.class)
			.addAnnotatedClass(ComplexUser.class)
			.addAnnotatedClass(BankEntity.class)
			.addAnnotatedClass(Credential.class)
			.addAnnotatedClass(FinancialAccountEntity.class)
			.addAnnotatedClass(BudgetEntity.class)
			.addAnnotatedClass(TransactionEntity.class)
			.addAnnotatedClass(CurrencyEntity.class)
			.addAnnotatedClass(StockEntity.class)
			.addAnnotatedClass(BondEntity.class)
			.addAnnotatedClass(StockTpcEntity.class)
			.addAnnotatedClass(BondTpcEntity.class)
			.addAnnotatedClass(PortfolioTpcEntity.class)
			.addAnnotatedClass(InvestmentStEntity.class)
			.addAnnotatedClass(StockStEntity.class)
			.addAnnotatedClass(BondStEntity.class)
			.addAnnotatedClass(PortfolioStEntity.class);

			return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
