package com.hibernatepractice.harness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.model.ComplexUser;
import com.hibernatepractice.model.Credential;
import com.hibernatepractice.model.FinancialAccountEntity;

public class ManyToManyBiDirectionalTestHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
			BankEntity bank = BankEntity.builder().bankName("Bank of America")
					.establishedDate(LocalDate.of(1999, Month.OCTOBER, 16))
					.contactNames(Arrays.asList("Kingshuk Mukherjee", "Deeksha Banerjee"))
					.build();

			ComplexUser user = ComplexUser.builder().firstName("Kingshuk").lastName("Mukherjee").userName("rishi2616")
					.dob(LocalDate.of(1986, Month.OCTOBER, 16)).createdDate(LocalDateTime.now())
					.lastUpdatedDate(LocalDateTime.now()).build();
			
			ComplexUser user2 = ComplexUser.builder().firstName("Deeksha").lastName("Banerjee").userName("rishi2616")
					.dob(LocalDate.of(1989, Month.OCTOBER, 26)).createdDate(LocalDateTime.now())
					.lastUpdatedDate(LocalDateTime.now()).build();

			Credential credential = Credential.builder().userName("rishi2616").password("sdjfhsdfgdsbfgdsyfg")
					.user(user).build();
			
			user.setCredential(credential);
			user2.setCredential(credential);
			
			Set<ComplexUser> users = new HashSet<>();
			users.add(user);
			users.add(user2);

			session.save(credential);
			
			FinancialAccountEntity account = FinancialAccountEntity.builder().accountName("BOA Account")
					.accountTypeId("3").bankId(bank).createdBy("Kingshuk Mukherjee").createdDate(LocalDateTime.now())
					.currentBalance(BigDecimal.valueOf(10000.00)).openedDate(LocalDateTime.now())
					.users(users)
					.build();
			
			FinancialAccountEntity account2 = FinancialAccountEntity.builder().accountName("DCU Account")
					.accountTypeId("3").bankId(bank).createdBy("Kingshuk Mukherjee").createdDate(LocalDateTime.now())
					.currentBalance(BigDecimal.valueOf(10000.00)).openedDate(LocalDateTime.now())
					.users(users)
					.build();
			
			Set<FinancialAccountEntity> accounts = new HashSet<>();
			accounts.add(account2);
			accounts.add(account);
			
			user.setAccounts(accounts);
			user2.setAccounts(accounts);
			
			session.save(account);

			session.getTransaction().commit();

			System.out.println(user);

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
