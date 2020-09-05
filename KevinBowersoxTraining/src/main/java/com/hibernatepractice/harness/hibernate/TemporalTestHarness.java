package com.hibernatepractice.harness.hibernate;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;

import com.hibernatepractice.config.HibernateConfigUtil;
import com.hibernatepractice.model.TemporalTestEntity;

public class TemporalTestHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();
			
			Date date = new Date();

			TemporalTestEntity testEntity = TemporalTestEntity.builder()
					.oldDate(date)
					.oldDateTime(date)
					.oldTime(date)
					.oldTimeStamp(date)
					.sqlDate(new java.sql.Date(date.getTime()))
					.sqlDateTime(new Timestamp(date.getTime()))
					.sqlTime(new Time(date.getTime()))
					.sqlTimeStamp(new Timestamp(date.getTime()))
					.build();
			
			session.save(testEntity);
			
			//This loads the data from the database again
			session.refresh(testEntity);

			System.out.println(testEntity.toString());

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
