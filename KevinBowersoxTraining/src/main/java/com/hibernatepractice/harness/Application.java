package com.hibernatepractice.harness;

import java.time.LocalDateTime;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.User;



public class Application {

	public static void main(String[] args) {
		try {
		Session session = HibernateConfigUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User user = User.builder().firstName("Kingshuk")
				      .lastName("Mukherjee")
				      .userName("rishi2616")
				      .createdDate(LocalDateTime.now())
				      .lastUpdatedDate(LocalDateTime.now())
				      .build();
		
		session.save(user);
		
		
		session.getTransaction().commit();
		session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
