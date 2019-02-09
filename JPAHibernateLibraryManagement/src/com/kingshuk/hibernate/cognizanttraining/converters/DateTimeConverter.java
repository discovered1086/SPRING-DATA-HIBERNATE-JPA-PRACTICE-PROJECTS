package com.kingshuk.hibernate.cognizanttraining.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<Date, java.sql.Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(Date date) {
        java.sql.Date returnDate = null;
        if (date != null) {
            returnDate = new java.sql.Date(date.getTime());
        }
        return returnDate;
    }

    @Override
    public Date convertToEntityAttribute(java.sql.Date date) {
        return date;
    }
}
