package it.fabrick.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import feign.FeignException;
import it.fabrick.test.filter.OperationFilter;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.OperationModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.service.OperazioniService;
import it.fabrick.test.utility.CurrencyEnum;

@SpringBootTest
class OperazioniMsApplicationTests {

	@Autowired
	OperazioniService operazioniService;
	@Value("${credenziali.accountId}")
	private Long accountId;
	private static final String transactionsStartDate = "2000-01-01";
	private static final String transactionsEndDate = "2020-06-01";
	private static final Double amount = 800d;
	private static final String description = "test";
	private static final Date now = new Date();
	private static final String user1 = "Jane Doe";
	
	
	@Test
	public void balanceTest() {
		BalanceModel balance = null;
		try {
			balance = operazioniService.getBalance(accountId);
		} catch (FeignException fe) {
			fail();
		}
		assertTrue(balance != null);
		assertEquals(accountId, balance.getAccountId());
		assertTrue(balance.getBalance() != null);
	}
	
	@Test
	public void transactionsTest() {
		List<TransactionModel> transactions = null;
		try {
			transactions = operazioniService.getTransactions(accountId, transactionsStartDate, transactionsEndDate);
		} catch (FeignException fe) {
			fail();
		}
		assertTrue(transactions != null);
		assertTrue(!transactions.isEmpty());
		assertEquals(accountId, transactions.get(0).getAccountId());
	}

	@Test
	public void moneyTransferTest() {
		OperationModel operation = null;
		OperationFilter filter = new OperationFilter();
		filter.setAmount(amount);
		filter.setCurrency(CurrencyEnum.EUR);
		filter.setDescription(description);
		filter.setExecutionDate(now);
		filter.setReceiverName(user1);
		try {
			operation = operazioniService.doMoneyTransfer(accountId, filter);
		} catch (FeignException fe) {
			fail();
		}
		assertTrue(operation != null);
		assertEquals(accountId, operation.getAccountId());
		assertTrue(operation.getTotalSpent() != null);
	}
}
