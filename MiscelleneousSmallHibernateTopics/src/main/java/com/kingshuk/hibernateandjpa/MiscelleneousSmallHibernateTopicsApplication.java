package com.kingshuk.hibernateandjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = "com.kingshuk.hibernateandjpa.mappingwithmapsidannotation")
public class MiscelleneousSmallHibernateTopicsApplication {


	public static void main(String[] args) {
		SpringApplication.run(MiscelleneousSmallHibernateTopicsApplication.class, args);
		
	}
}
