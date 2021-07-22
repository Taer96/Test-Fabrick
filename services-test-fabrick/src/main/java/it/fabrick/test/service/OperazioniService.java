package it.fabrick.test.service;

import org.springframework.stereotype.Service;

import it.fabrick.test.model.BalanceModel;

@Service
public interface OperazioniService {

	/**
	 * metodo per ottenere il saldo di un account
	 * @param accountId
	 * @return
	 */
	BalanceModel getBalance(Long accountId);

}
