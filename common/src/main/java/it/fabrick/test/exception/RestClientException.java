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
	private final Integer code;
	private final String errors;

	public RestClientException(Integer code, String message) {
		super(message);
		this.code = code;
		this.errors = null;
	}

	public RestClientException(String message) {
		super(message);
		this.code = null;
		this.errors = null;
	}

	public RestClientException(String message, Throwable cause){
        super(message, cause);
        this.code = null;
        this.errors = null;
	}	

	public RestClientException(Integer code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
        this.errors = null;
	}
	
	public RestClientException(Integer code, String message, String errors) {
		super(message);
		this.code = code;
		this.errors = errors;
	}

	public RestClientException(String message, String errors) {
		super(message);
		this.code = null;
		this.errors = errors;
	}

	public RestClientException(String message, Throwable cause, String errors){
        super(message, cause);
        this.code = null;
        this.errors = errors;
	}	

	public RestClientException(Integer code, String message, Throwable cause, String errors){
        super(message, cause);
        this.code = code;
        this.errors = errors;
	}
}
