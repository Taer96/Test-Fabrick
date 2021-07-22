package it.fabrick.test.model;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class BalanceModel implements Serializable {
	
	private static final long serialVersionUID = 2558532965695747557L;
	private Long accountId;
	private Date date;
	private Float balance;
	private Float availableBalance;
	private CurrencyEnum currency;
}
