package it.fabrick.test.dto;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class BalanceDTO implements Serializable {

	private static final long serialVersionUID = -3949766244099251596L;
	@CustomDate
	private Date date;
	private Float balance;
	private Float availableBalance;
	private CurrencyEnum currency;
}
