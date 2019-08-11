package com.financemanagement.domaindevelopment.sequencegenerators.newsequences;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class NewAccountSequenceGenerator extends SequenceStyleGenerator{
	
	private String valuePrefix;
	
	private String numberFormat;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
		super.configure(LongType.INSTANCE, params, serviceRegistry);
		valuePrefix = ConfigurationHelper.getString("stringPrefix", params, "");
		numberFormat = ConfigurationHelper.getString("numberFormat", params, "%d");
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object){
		return valuePrefix +
				String.format(numberFormat, super.generate(session, object));
	}
	
	

}
