package it.fabrick.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fabrick.test.assembler.CommonAssembler;
import it.fabrick.test.assembler.EntityAssembler;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.ResponseDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.dto.filter.MoneyTransferFilterDTO;
import it.fabrick.test.feignclient.OperazioniClient;
import it.fabrick.test.filter.OperationFilter;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.OperationModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.repository.TransactionRepository;
import it.fabrick.test.service.OperazioniService;

@Service
public class OperazioniServiceImpl implements OperazioniService {
	
	@Autowired
	protected CommonAssembler assembler;
	@Autowired
	protected OperazioniClient operazioniClient;
	@Autowired
	protected TransactionRepository transactionRepository;
	@Autowired
	protected EntityAssembler entityAssembler;
	
	@Override
	public BalanceModel getBalance(Long accountId) {
		BalanceModel output = null;
		// non gestisco a questo livello la FallbackException perchè è una runtime Exception
		ResponseDTO<BalanceDTO> result = operazioniClient.getBalance();
		if (result != null && result.getPayload() != null) {
			output = assembler.assembleBalance(result.getPayload(), accountId);
		}
		return output;
	}

	@Override
	public List<TransactionModel> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) {
		List<TransactionModel> output = null;
		// non gestisco a questo livello la FallbackException perchè è una runtime Exception
		ResponseDTO<ListOutputDTO<TransactionDTO>> result = operazioniClient.getTransactions(fromAccountingDate, toAccountingDate);
		if (result != null && result.getPayload() != null && !result.getPayload().getList().isEmpty()) {
			output = assembler.assembleTransactions(result.getPayload().getList(), accountId);
			List<Long> ids = transactionRepository.findAll().stream().map(t -> t.getTransactionId()).collect(Collectors.toList());
			for (TransactionModel t : output) {
				if (!ids.contains(t.getTransactionId())) {
					transactionRepository.save(entityAssembler.assembleTransaction(t));
				}
			}
			transactionRepository.flush();
		}
		return output;
	}
	
	@Override
	public OperationModel doMoneyTransfer(Long accountId, OperationFilter filter) {
		OperationModel output = null;
		MoneyTransferFilterDTO moneyFilter = new MoneyTransferFilterDTO();
		
		ResponseDTO<MoneyTransferDTO> result = operazioniClient.doMoneyTransfer(moneyFilter);
		if (result != null && result.getPayload() != null) {
			output = assembler.assembleOperation(result.getPayload(), accountId);
		}
		return output;
	}
}
