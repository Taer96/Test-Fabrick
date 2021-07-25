package it.fabrick.test.dto;

import java.io.Serializable;

import lombok.Data;

@Data
abstract class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 4469600442951335352L;
	private String name;
	private AccountLightDTO account;
}
