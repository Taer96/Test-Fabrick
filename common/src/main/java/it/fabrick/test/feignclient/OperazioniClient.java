package it.fabrick.test.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.ResponseDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.dto.filter.MoneyTransferFilterDTO;
import it.fabrick.test.feignclient.utility.FeignConfig;

@FeignClient(value = "${endpoint.name.operazioni}", url = "${endpoint.base}", configuration = FeignConfig.class)
public interface OperazioniClient {

	// /api/gbs/banking/v4.0/accounts/{accountId}/balance
	@GetMapping(value = "${endpoint.getSaldo}", produces = "application/json")
	ResponseDTO<BalanceDTO> getBalance();
	// /api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers
	@PostMapping(value = "${endpoint.moneyTransfer}", produces = "application/json", consumes = "application/json")
	ResponseDTO<MoneyTransferDTO> doMoneyTransfer(@RequestBody MoneyTransferFilterDTO filter);
	// /api/gbs/banking/v4.0/accounts/{accountId}/transactions
	@GetMapping(value = "${endpoint.getTransazioni}", produces = "application/json")
	ResponseDTO<ListOutputDTO<TransactionDTO>> getTransactions(@RequestParam("fromAccountingDate") String fromAccountingDate, @RequestParam("toAccountingDate") String toAccountingDate);
}
