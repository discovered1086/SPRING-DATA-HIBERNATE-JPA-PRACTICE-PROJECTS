package com.financemanagement.domaindevelopment.mainclass.deserialization;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateTimeDeSerializer extends StdDeserializer<ZonedDateTime> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3202949066610693761L;

	protected DateTimeDeSerializer(Class<?> vc) {
		super(vc);
	}

	@SuppressWarnings("unused")
	private DateTimeDeSerializer() {
		this(null);
	}

	@Override
	public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

		String dateTime = p.getText();

		return ZonedDateTime.parse(dateTime, FinanceManagementSerialization.dateTimeFormatter).truncatedTo(ChronoUnit.SECONDS);

	}

}
