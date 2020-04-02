package com.kingshuk.hibernateandjpa.hibernatesoftdelete.runners;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.kingshuk.hibernateandjpa.hibernatesoftdelete.model.Account;
import com.kingshuk.hibernateandjpa.hibernatesoftdelete.repositories.AccountRepository;

//@Component
public class MyVeryOwnMainApplication implements CommandLineRunner {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void run(String... args) throws Exception {
		// accountRepository.deleteById(2L);

		TypedQuery<Account> allAccountsQuery = entityManager.createNamedQuery("Account.FindByAccountName",
				Account.class);

		allAccountsQuery.setParameter("accountName", "%HDFC%");

		allAccountsQuery.getResultList().forEach(System.out::println);
		
		System.out.println("\n*************\n Printing all active accounts\n");
		
		accountRepository.findAll().forEach(System.out::println);
	}

}
