package com.financemanagement.domaindevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(type = FilterType.ASPECTJ, 
pattern = "(com.financemanagement.domaindevelopment.allmodels.*)"))
public class FinancialManagementDomainDevelopmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialManagementDomainDevelopmentApplication.class, args);
	}

}
