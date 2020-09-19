package com.kingshuk.jpahibernate.plsql;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.kingshuk.jpahibernate.plsql.config.HibernateConfigUtil;

public class CallingStoredProcedureHarness {

	public static void main(String[] args) {
		try (Session session = HibernateConfigUtil.getSessionFactory().openSession();) {

			session.beginTransaction();

			StoredProcedureQuery query = session.createStoredProcedureQuery("GET_EMPLOYEE_PASSPORT")
					.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT).setParameter(1, "66");

			query.execute();

			String parameterValue = (String) query.getOutputParameterValue(2);
			
			System.out.println("The passport number of the employee 66 is: " + parameterValue);
			
			session.getTransaction().commit();

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
