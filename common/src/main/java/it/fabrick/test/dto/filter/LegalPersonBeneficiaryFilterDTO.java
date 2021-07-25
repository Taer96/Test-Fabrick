package it.fabrick.test.dto.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class LegalPersonBeneficiaryFilterDTO implements Serializable {

	private static final long serialVersionUID = 6494676527940164197L;
	private String fiscalCode;
	private String legalRepresentativeFiscalCode;
}
