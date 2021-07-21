package it.fabrick.test.dto;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class TransactionDTO implements Serializable {

	private static final long serialVersionUID = 220395386195565859L;
	private Long transactionId;
	private String operationId;
	@CustomDate
	private Date accountingDate;
	@CustomDate
	private Date valueDate;
	private ValueDTO value;
	private Float amount;
	private CurrencyEnum currency;
	private String description;
}
