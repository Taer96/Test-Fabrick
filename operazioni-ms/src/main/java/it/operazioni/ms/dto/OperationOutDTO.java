package it.operazioni.ms.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OperationOutDTO implements Serializable {

	private static final long serialVersionUID = 455071319461014316L;
	private Long accountId;
	private String status;
	private String direction;
	private String receiverName;
	private String receiverIBAN;
	private Double amount;
	private String currencyUsed;
	private Double feesAmount;
	private Double totalSpent;
	private Date operationDate;
}
