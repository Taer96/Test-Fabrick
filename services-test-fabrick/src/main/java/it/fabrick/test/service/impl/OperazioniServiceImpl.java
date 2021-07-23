package it.fabrick.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fabrick.test.assembler.CommonAssembler;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.ResponseErrorDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.feignclient.OperazioniClient;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.service.OperazioniService;

@Service
public class OperazioniServiceImpl implements OperazioniService {
	
	@Autowired
	protected CommonAssembler assembler;
	@Autowired
	protected OperazioniClient operazioniClient;
	
	@Override
	public BalanceModel getBalance(Long accountId) {
		BalanceModel output = null;
		// non gestisco a questo livello la FallbackException perchè è una runtime Exception
		ResponseErrorDTO<BalanceDTO> result = operazioniClient.getSaldoAccount();
		if (result != null && result.getPayload() != null) {
			output = assembler.assembleBalance(result.getPayload(), accountId);
		}
		return output;
	}

	@Override
	public List<TransactionModel> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) {
		List<TransactionModel> output = null;
		// non gestisco a questo livello la FallbackException perchè è una runtime Exception
		ResponseErrorDTO<ListOutputDTO<TransactionDTO>> result = operazioniClient.getTransazioni(fromAccountingDate, toAccountingDate);
		if (result != null && result.getPayload() != null && !result.getPayload().getList().isEmpty()) {
			output = assembler.assembleTransactions(result.getPayload().getList(), accountId);
		}
		return output;
	}
}
