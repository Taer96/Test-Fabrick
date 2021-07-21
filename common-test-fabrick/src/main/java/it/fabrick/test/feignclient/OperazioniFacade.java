package it.fabrick.test.feignclient;

import java.util.Date;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.fabrick.test.annotation.CustomDate;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.dto.filter.MoneyTransferFilterDTO;
import it.fabrick.test.feignclient.utility.FeignConfig;

@FeignClient(value = "${endpoint.name.operazioni}", url = "${endpoint.base}", configuration = {FeignConfig.class})
public interface OperazioniFacade {

	// /api/gbs/banking/v4.0/accounts/{accountId}/balance
	@RequestMapping(method = RequestMethod.GET, value = "${endpoint.getSaldo}", consumes = "application/json")
	ListOutputDTO<BalanceDTO> getSaldoAccount();
	// /api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers
	@RequestMapping(method = RequestMethod.POST, value = "${endpoint.transferMoney}", consumes = "application/json")
	MoneyTransferDTO trasferisciSoldi(@RequestBody MoneyTransferFilterDTO filter);
	// /api/gbs/banking/v4.0/accounts/{accountId}/transactions
	@RequestMapping(method = RequestMethod.GET, value = "${endpoint.getTransazioni}", consumes = "application/json")
	ListOutputDTO<TransactionDTO> getTransazioni(@RequestParam("fromAccountingDate") @CustomDate Date fromAccountingDate, @RequestParam("toAccountingDate") @CustomDate Date toAccountingDate);
}
