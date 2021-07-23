package it.fabrick.test.model;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class TransactionModel implements Serializable {

	private static final long serialVersionUID = -3268457220192031883L;
	private Long accountId;
	private Long transactionId;
	private String operationId;
	@CustomDate
	private Date accountingDate;
	@CustomDate
	private Date valueDate;
	private ValueModel type;
	private Float amount;
	private CurrencyEnum currency;
	private String description;
}
