package com.financemanagement.domaindevelopment.allmodels.sequencegenerators;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;


public class TransactionSequenceGenerator implements IdentifierGenerator{
	
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		
		@SuppressWarnings("unchecked")
		Query<String> sequenceValue = session.createNativeQuery("SELECT LPAD(spring_boot_practice_projects.transaction_sequence.nextval,15, '0') FROM dual");
		
		
		String nextSequenceValue = String.valueOf(sequenceValue.getSingleResult());
		
		return "TRN".concat(nextSequenceValue);
	}

}
