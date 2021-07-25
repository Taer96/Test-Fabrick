package it.fabrick.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.fabrick.test.filter.OperationFilter;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.OperationModel;
import it.fabrick.test.model.TransactionModel;

@Service
public interface OperazioniService {

	/**
	 * metodo per ottenere il saldo di un account
	 * @param accountId
	 * @return
	 */
	BalanceModel getBalance(Long accountId);

	/**
	 * metodo per ottenere le transazioni effettuate e ricevute nel periodo scelto
	 * @param accountId
	 * @param fromAccountingDate
	 * @param toAccountingDate
	 * @return
	 */
	List<TransactionModel> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate);

	/**
	 * metodo per effettuare una transazione da un account ad un altro
	 * @param accountId
	 * @param filter
	 * @return
	 */
	OperationModel doMoneyTransfer(Long accountId, OperationFilter filter);

}
