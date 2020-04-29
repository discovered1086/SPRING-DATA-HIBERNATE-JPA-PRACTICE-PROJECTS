package com.financemanagement.domaindevelopment.allmodels.sequencegenerators;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

//@Slf4j
public class CustomerSequenceGenerator implements IdentifierGenerator{
	
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object){
		//log.info(String.format("The session value is %s",session));
		
		Session session2 = Session.class.cast(session);
		
		@SuppressWarnings("unchecked")
		Query<String> sequenceValue = session2.createNativeQuery("SELECT LPAD(spring_boot_practice_projects.customer_sequence.nextval,10, '0') FROM dual");
		
		
		String nextSequenceValue = String.valueOf(sequenceValue.getSingleResult());
		
		return "CUST".concat(nextSequenceValue);
	}

}
