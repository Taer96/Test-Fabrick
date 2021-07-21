package it.fabrick.test.dto;

import java.io.Serializable;
import java.util.Date;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.utility.CurrencyEnum;
import lombok.Data;

@Data
public class AccountDTO implements Serializable {

	private static final long serialVersionUID = 7980854602671452597L;
	private Long accountId;
    private String iban;
    private Long abiCode;
    private Long cabCode;
    private String countryCode; //enum?
    private Long internationalCin;
    private String nationalCin;
    private Long account;
    private String alias;
    private String productName;
    private String holderName;
    @CustomDate
    private Date activatedDate;
    private CurrencyEnum currency;
}
