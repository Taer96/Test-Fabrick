package it.fabrick.test.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResponseDTO <T> implements Serializable {

	private static final long serialVersionUID = 2958650520374249664L;
	private String status;
	private T payload;
	// in caso non ci siano errori torna solamente error, ma in quel caso non importa
	private List<ErrorDTO> errors;
}
