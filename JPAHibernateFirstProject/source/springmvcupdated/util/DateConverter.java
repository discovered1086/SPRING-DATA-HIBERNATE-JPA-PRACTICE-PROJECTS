package springmvcupdated.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kingshuk on 7/8/17.
 */
@Converter
public class DateConverter implements AttributeConverter<Date, String> {


    @Override
    public String convertToDatabaseColumn(Date date) {
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }

    @Override
    public Date convertToEntityAttribute(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }
}
