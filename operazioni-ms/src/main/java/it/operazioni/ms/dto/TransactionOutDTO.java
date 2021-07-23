package it.operazioni.ms.dto;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class TransactionOutDTO implements Serializable {

	private static final long serialVersionUID = -2404814566877261491L;
	private Long accountId;
	private Long transactionId;
	private String operationId;
	@CustomDate
	private Date accountingDate;
	@CustomDate
	private Date valueDate;
	private ValueOutDTO type;
	private Float amount;
	private CurrencyEnum currency;
	private String description;
}
