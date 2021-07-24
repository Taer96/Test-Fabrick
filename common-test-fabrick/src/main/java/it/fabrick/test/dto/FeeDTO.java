package it.fabrick.test.dto;

import java.io.Serializable;

import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class FeeDTO implements Serializable {

	private static final long serialVersionUID = 4787959144930802553L;
	private String feeCode;
	private String description;
	private Double amount;
	private CurrencyEnum currency;
}
