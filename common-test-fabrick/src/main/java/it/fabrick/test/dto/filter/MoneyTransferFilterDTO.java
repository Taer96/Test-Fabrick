package it.fabrick.test.dto.filter;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class MoneyTransferFilterDTO implements Serializable {

	private static final long serialVersionUID = 1557219223636897780L;
	private CreditorFilterDTO creditor;
	@CustomDate
	private Date executionDate;
	private String uri;
    private String description;
    private Double amount;
    private CurrencyEnum currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType; //enum?
    private Long feeAccountId;
    private TaxReliefFilterDTO taxRelief;
}
