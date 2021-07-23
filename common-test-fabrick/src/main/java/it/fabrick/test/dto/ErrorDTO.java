package it.fabrick.test.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = -4570505755600189277L;
	private String code;
	private String description;
	private String params;
}
