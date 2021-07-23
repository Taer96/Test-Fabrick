package it.fabrick.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.TransactionModel;

@Service
public interface OperazioniService {

	/**
	 * metodo per ottenere il saldo di un account
	 * @param accountId
	 * @return
	 */
	BalanceModel getBalance(Long accountId);

	List<TransactionModel> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate);

}
