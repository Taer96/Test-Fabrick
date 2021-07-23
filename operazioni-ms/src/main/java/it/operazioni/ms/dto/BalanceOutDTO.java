package it.operazioni.ms.dto;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class BalanceOutDTO implements Serializable {

	private static final long serialVersionUID = 66101122542173581L;
	private Long accountId;
	@CustomDate
	private Date date;
	private Float balance;
	private Float availableBalance;
	private CurrencyEnum currency;
}
