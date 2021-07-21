package it.fabrick.test.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.fabrick.test.annotation.CustomDate;
import lombok.Data;

@Data
public class MoneyTransferDTO implements Serializable {

	private static final long serialVersionUID = 8640285011774875851L;
	private Long moneyTransferId;
	private String status; //enum?
	private String direction; //enum?
	private CreditorDTO creditor;
	private DebtorDTO debtor;
	private Long cro;
	private String uri;
	private String trn;
	private String description;
	private Date createdDatetime;
	private Date accountedDatetime;
	@CustomDate
	private Date debtorValueDate;
	@CustomDate
	private Date creditorValueDate;
	private AmountDTO amount;
	private Boolean isUrgent;
	private Boolean idInstant;
	private String feeType; //enum?
	private Long feeAccountId;
	private List<FeeDTO> fees;
	private Boolean hasTaxRelief;
}
