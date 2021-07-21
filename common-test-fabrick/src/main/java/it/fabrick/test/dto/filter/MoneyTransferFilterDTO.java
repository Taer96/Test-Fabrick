package it.fabrick.test.dto.filter;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class MoneyTransferFilterDTO implements Serializable {

	private static final long serialVersionUID = 1557219223636897780L;
	public CreditorFilterDTO creditor;
	@CustomDate
	public Date executionDate;
    public String uri;
    public String description;
    public Float amount;
    public CurrencyEnum currency;
    public Boolean isUrgent;
    public Boolean isInstant;
    public String feeType; //enum?
    public Long feeAccountId;
    public TaxReliefFilterDTO taxRelief;
}
