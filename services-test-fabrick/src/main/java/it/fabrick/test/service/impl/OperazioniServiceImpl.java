package it.fabrick.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fabrick.test.assembler.CommonAssembler;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.feignclient.OperazioniFacade;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.service.OperazioniService;

@Service
public class OperazioniServiceImpl implements OperazioniService {
	
	@Autowired
	protected CommonAssembler assembler;
	@Autowired
	protected OperazioniFacade operazioniFacade;
	
	//TODO: da finire
	@Override
	public BalanceModel getBalance(Long accountId) {
		BalanceDTO result = null;
		try {
			result = operazioniFacade.getSaldoAccount();
		} catch (Exception e) {
			
		}
		return assembler.assembleBalance(result, accountId);
	}
}
