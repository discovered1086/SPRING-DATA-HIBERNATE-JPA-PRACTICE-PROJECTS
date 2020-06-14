package com.hibernatepractice.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hibernatepractice.model.Address;
import com.hibernatepractice.model.BankEntity;
import com.hibernatepractice.model.TransactionEntity;

public interface EntityCreationUtil {

	static BankEntity createBankEntity(String bankName) {
		Address address = Address.builder().addressLine1("1525 Busch Pkwy").addressLine2("Room No 327")
				.city("Buffalo Grove").state("IL").zipCode("60089").build();

		Address address2 = Address.builder().addressLine1("771 Shady Grove Ln").city("Buffalo Grove").state("IL")
				.zipCode("60089").build();

		Map<String, String> locations = new HashMap<>();
		locations.put("HCT", "Hartford CT");
		locations.put("CIL", "Chicago IL");

		return BankEntity.builder().bankName(bankName).establishedDate(LocalDate.of(1999, Month.OCTOBER, 16))
				.contactNames(Arrays.asList("Kingshuk Mukherjee", "Deeksha Banerjee"))
				.address(Arrays.asList(address, address2)).locations(locations).build();

	}
	
	static List<TransactionEntity> createTransactions(){
		TransactionEntity transaction1 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(20.00))
				.createdBy("Kingshuk Mukherjee").createdDate(LocalDateTime.now()).transactionType("Debit")
				.transactionNotes("Shoe purchase")
				.build();

		TransactionEntity transaction2 = TransactionEntity.builder().transactionAmount(BigDecimal.valueOf(50.00))
				.createdBy("Deeksha Banerjee").createdDate(LocalDateTime.now()).transactionType("Debit")
				.transactionNotes("Grocery").build();
		
		return Arrays.asList(transaction1, transaction2);
	}

}
