package it.fabrick.test.dto;

import java.io.Serializable;

import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class AmountDTO implements Serializable {

	private static final long serialVersionUID = -2205240882174065278L;
	private Float debtorAmount;
	private CurrencyEnum debtorCurrency;
	private Float creditorAmount;
	private CurrencyEnum creditorCurrency;
}
