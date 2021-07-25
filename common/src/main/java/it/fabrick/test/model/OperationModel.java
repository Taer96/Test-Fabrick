package it.fabrick.test.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OperationModel implements Serializable {

	private static final long serialVersionUID = -7751437695564194012L;
	private Long accountId;
	private String status;
	private String direction;
	private String receiverName;
	private String receiverIBAN;
	private Double amountSent;
	private String currencyUsed;
	private Double feesAmount;
	private Double totalSpent;
	private Date operationDate;
}
