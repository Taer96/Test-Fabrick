package it.fabrick.test.dto;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class AmountDTO implements Serializable {

	private static final long serialVersionUID = -2205240882174065278L;
	private Double debtorAmount;
	private CurrencyEnum debtorCurrency;
	private Double creditorAmount;
	private CurrencyEnum creditorCurrency;
	@CustomDate
	private Date creditorCurrencyDate;
	private Double exchangeRate;
}
