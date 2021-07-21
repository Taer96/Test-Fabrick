package it.fabrick.test.feignclient.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

/**
 * classe per implementare configurazioni da utilizzare nel feign client
 * 
 * @author gpiacentini
 */
public class FeignConfig {

	@Value("${credenziali.header.auth-Schema}")
	private String authSchemaValue;
	@Value("${credenziali.header.api-Key}")
	private String apiKeyValue;
	@Value("${credenziali.header.timezone}")
	private String timezoneValue;
	
	/**
	 * bean per intercettare le chiamate verso i servizi esterni
	 */
	@Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
        	// inserisco l'header Auth-Schema con il relativo valore
        	template.header(HeaderProperties.AUTH_SCHEMA, authSchemaValue);
            // inserisco l'header Api-Key con il relativo valore
        	template.header(HeaderProperties.API_KEY, apiKeyValue);
            // inserisco l'header X-Time-Zone con il relativo valore
        	template.header(HeaderProperties.TIMEZONE, timezoneValue);
        };
    }
}
