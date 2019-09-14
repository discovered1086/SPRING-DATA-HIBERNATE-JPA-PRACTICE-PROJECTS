package com.kingshuk.hibernateandjpa.mappingwithmapsidannotation;

import java.time.ZonedDateTime;
import java.util.Date;

import org.hibernate.Session;

import com.kingshuk.hibernateandjpa.config.HibernateConfigUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapsIdTestHarness {

	public static void main(String[] args) {
		Employee employee = Employee.builder().firstName("Kingshuk")
				.lastName("Mukherjee").hireDate(new Date()).build();
		
		ZonedDateTime nowTime = ZonedDateTime.now();
		
		log.info("The time is: "+nowTime.toString());
		

		EmployeePassport employeePassport = EmployeePassport.builder()
				.employee(employee).passportNumber("J4852858")
				.issueDate(nowTime).build();

		Session session = HibernateConfigUtil.getSessionFactory().openSession();
		
		session.getTransaction().begin();
		
		session.save(employeePassport);
		
		session.getTransaction().commit();
		
		session.close();
	}

}
