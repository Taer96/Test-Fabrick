package it.fabrick.test.annotation.utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.fabrick.test.constants.DateConstants;

/**
 * classe utilizzabile dall'annotation CustomDate per serializzare le date con il pattern yyyy-mm-dd
 * 
 * @author gpiacentini
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if(value != null) {
			gen.writeString(new SimpleDateFormat(DateConstants.PATTERN).format(value));
		} else {
			gen.writeNull();
		}
	}

}
