package it.fabrick.test.dto.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class NaturalPersonBeneficiaryFilterDTO implements Serializable {

	private static final long serialVersionUID = 3917705271970753508L;
	private String fiscalCode1;
	private String fiscalCode2;
	private String fiscalCode3;
	private String fiscalCode4;
	private String fiscalCode5;
}
