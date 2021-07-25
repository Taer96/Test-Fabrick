package it.fabrick.test.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressDTO implements Serializable {

	private static final long serialVersionUID = 567926303523574780L;
	private String address;
	private String city;
	private String countryCode;
}
