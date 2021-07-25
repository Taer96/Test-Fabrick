package it.fabrick.test.dto.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreditorFilterDTO implements Serializable {
	
	private static final long serialVersionUID = -8914942696064518686L;
	private String name;
	private AccountFilterDTO account;
	private AddressFilterDTO address;
}
