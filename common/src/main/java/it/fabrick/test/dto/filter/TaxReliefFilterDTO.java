package it.fabrick.test.dto.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaxReliefFilterDTO implements Serializable {

	private static final long serialVersionUID = 8521160738322801398L;
	private Long taxReliefId;
	private Boolean isCondoUpgrade;
	private String creditorFiscalCode;
	private String beneficiaryType; //Enum?
	private NaturalPersonBeneficiaryFilterDTO naturalPersonBeneficiary;
	private LegalPersonBeneficiaryFilterDTO legalPersonBeneficiary;
}
