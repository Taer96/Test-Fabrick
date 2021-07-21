package it.fabrick.test.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreditorDTO extends CustomerDTO {

	private static final long serialVersionUID = 3680242100202401847L;
	private AddressDTO address;
}
