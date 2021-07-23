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
public class FallbackException extends RuntimeException {

	private static final long serialVersionUID = -5932595197192955509L;
	private final Integer code;

	public FallbackException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public FallbackException(String message) {
		super(message);
		this.code = null;
	}

	public FallbackException(String message, Throwable cause){
        super(message, cause);
        this.code = null;
	}	

	public FallbackException(Integer code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
	}
}
