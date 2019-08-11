package com.financemanagement.domaindevelopment.harnesses;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financemanagement.domaindevelopment.configuration.ConfigurationUtil;
import com.financemanagement.domaindevelopment.model.BankAccount;

public class BankAccountScenarioFirstHarness {
	
	
	public static void main(String[] args) {

		Transaction theTransaction = null;
		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			
			//Load the object to memory from the json file
			ObjectMapper objectMapper = new ObjectMapper();
			
			BankAccount bankAccount = objectMapper.readValue(
					new File("jsonfiles/BankAccount.json"), BankAccount.class);

			// Begin the transaction
			theTransaction = session.beginTransaction();
			
			bankAccount.getAccountHolder().getAddressList().forEach(session::save);
			
			session.save(bankAccount.getAccountHolder());
			
			//Persist the bank account
			session.save(bankAccount);
			
			// Commit the transaction
			theTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();

			if (theTransaction != null) {
				theTransaction.rollback();
			}
		}
	}

}
