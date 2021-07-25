package it.fabrick.test.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountLightDTO implements Serializable {

	private static final long serialVersionUID = -7625327637341880863L;
	private String accountCode;
	private String bicCode;
}