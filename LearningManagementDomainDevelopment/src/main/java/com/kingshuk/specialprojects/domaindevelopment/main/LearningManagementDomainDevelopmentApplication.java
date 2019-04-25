package com.kingshuk.specialprojects.domaindevelopment.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {
		"com.kingshuk.specialprojects.domaindevelopment.main.models.resource",
		"com.kingshuk.specialprojects.domaindevelopment.main.models.sequencegenerators",
		"com.kingshuk.specialprojects.domaindevelopment.main.models.topic",
		"com.kingshuk.specialprojects.domaindevelopment.main.models.learningtrack"
})
@SpringBootApplication
public class LearningManagementDomainDevelopmentApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LearningManagementDomainDevelopmentApplication.class, args);
		
	}
	
}
