package it.fabrick.test.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * exception per gestire gli errori ricevuti dal feign client
 * 
 * @author gpiacentini
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RestClientException extends RuntimeException {

	private static final long serialVersionUID = -5932595197192955509L;
	private final String code;
	private final String errors;
	private final Integer statusCode;

	public RestClientException(String message) {
		super(message);
		this.code = null;
		this.errors = null;
		this.statusCode = 500;
	}

	public RestClientException(String message, String code, String errors, Integer statusCode) {
		super(message);
		this.code = code;
		this.errors = errors;
		this.statusCode = statusCode;
	}
}
