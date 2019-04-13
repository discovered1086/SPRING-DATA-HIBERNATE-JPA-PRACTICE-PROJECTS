package com.kingshuk.hibernateandjpa.hibernatesoftdelete.runners;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.kingshuk.hibernateandjpa.hibernatesoftdelete.model.Account;
import com.kingshuk.hibernateandjpa.hibernatesoftdelete.model.AccountState;
import com.kingshuk.hibernateandjpa.hibernatesoftdelete.repositories.AccountRepository;

@Component
public class StartUpRunner implements ApplicationRunner {
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (accountRepository.findAll().isEmpty()) {
			Account account1 = Account.builder().accountNumber("30911566592").accountDesc("SBI account")
					.accountName("SBI Account").accountState(AccountState.ACTIVE).build();
			Account account2 = Account.builder().accountNumber("876465465465").accountDesc("HDFC account")
					.accountName("HDFC account").accountState(AccountState.ACTIVE).build();
			Account account3 = Account.builder().accountNumber("89789745445").accountDesc("BOA account")
					.accountName("BOA account").accountState(AccountState.ACTIVE).build();

			List<Account> accountList = new LinkedList<>();

			accountList.add(account1);
			accountList.add(account2);
			accountList.add(account3);

			accountList.forEach(accountRepository::save);
		}
	}
}
