package it.operazioni.ms.dto.filter;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class OperationInDTO implements Serializable {

	private static final long serialVersionUID = -594695933702836339L;
	private String receiverName;
	private String description;
	private CurrencyEnum currency;
	private Double amount;
	@CustomDate
	private Date executionDate;
}
