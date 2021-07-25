package it.fabrick.test.dto.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressFilterDTO implements Serializable {

	private static final long serialVersionUID = -7420491324584361124L;
	private String address;
	private String city;
	private String countryCode;
}
