package com.financemanagement.domaindevelopment.mainclass.deserialization;

import java.time.format.DateTimeFormatter;

public interface FinanceManagementSerialization {
	//static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a zzzz");

	static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
	
	static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE;
}
