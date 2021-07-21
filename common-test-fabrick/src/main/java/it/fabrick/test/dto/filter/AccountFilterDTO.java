package it.fabrick.test.dto.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountFilterDTO implements Serializable {

	private static final long serialVersionUID = -7625327637341880863L;
	private String accountCode;
	private String bicCode;
}
