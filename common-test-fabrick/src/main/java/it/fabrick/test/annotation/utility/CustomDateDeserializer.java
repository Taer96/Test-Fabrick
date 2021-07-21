package it.fabrick.test.annotation.utility;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import it.fabrick.test.constants.DateConstants;

/**
 * classe utilizzabile dall'annotation CustomDate per deserializzare le date con il pattern yyyy-mm-dd
 * 
 * @author gpiacentini
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> implements ContextualDeserializer {

	private  JsonDeserializer<String> baseDeserializer = null;
	private  BeanProperty property = null;
	
	public CustomDateDeserializer() { }
	
	public CustomDateDeserializer(final JsonDeserializer<String> wrapped, final BeanProperty property) {
        this.baseDeserializer = wrapped;
        this.property = property;
    }
	
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		return new CustomDateDeserializer(baseDeserializer, property);
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Date output = null;
		JsonDeserializer<?> deserializer = baseDeserializer;
		JsonNode node = p.getCodec().readTree(p);
        if (deserializer instanceof ContextualDeserializer) {
            deserializer = ((ContextualDeserializer) deserializer).createContextual(ctxt, property);
        }
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat(DateConstants.PATTERN, Locale.ITALY);
        	sdf.setTimeZone(TimeZone.getTimeZone(DateConstants.TIMEZONE));
			output = sdf.parse(node.asText());
		} catch (ParseException e) { }
        
        return output;
	}

}
