package it.fabrick.test.dto;

import java.io.Serializable;

import lombok.Data;

@Data
abstract class ResponseDTO <T> implements Serializable {

	private static final long serialVersionUID = 2958650520374249664L;
	private String status;
	private T payload;
}
