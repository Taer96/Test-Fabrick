package it.fabrick.test.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.fabrick.test.dto.AccountDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.filter.MoneyTransferFilterDTO;
import it.fabrick.test.feignclient.utility.FeignConfig;

@FeignClient(value = "${endpoint.name.operazioni}", url = "${endpoint.base}", configuration = {FeignConfig.class})
public interface OperazioniFacade {

	// /api/gbs/banking/v4.0/accounts
	@RequestMapping(method = RequestMethod.GET, value = "${endpoint.getClienti}", consumes = "application/json")
	ListOutputDTO<AccountDTO> getDatiAccount();
	// /api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers
	@RequestMapping(method = RequestMethod.POST, value = "${endpoint.transferMoney}", consumes = "application/json")
	MoneyTransferDTO trasferisciSoldi(@RequestBody MoneyTransferFilterDTO filter);
}
