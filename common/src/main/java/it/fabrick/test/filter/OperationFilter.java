package it.fabrick.test.filter;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class OperationFilter implements Serializable {

	private static final long serialVersionUID = 7244439537724298928L;
	private String creditorIBAN;
	private String receiverName;
	private String description;
	private CurrencyEnum currency;
	private Double amount;
	private Date executionDate;
	private Boolean condoUpgrade;
	private String creditorPIVAOrFiscalCode;
	private String beneficiaryType;
	private String creditorFiscalCode;
}
