package com.kingshuk.hibernateandjpa.mappingwithmapsidannotation;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MapsIdTestRunner implements ApplicationRunner{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Employee employee = Employee.builder().firstName("Kingshuk")
						  .lastName("Mukherjee")
						  .hireDate(new Date())
						  .build();
		
		EmployeePassport employeePassport = EmployeePassport.builder().employee(employee)
								  .passportNumber("J4852858")
								  .issueDate(ZonedDateTime.now())
								  .build();
		
		entityManager.persist(employeePassport);
		
		entityManager.close();
	}



}
