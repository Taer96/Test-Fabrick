package it.fabrick.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feign.FeignException;
import it.fabrick.test.assembler.CommonAssembler;
import it.fabrick.test.assembler.EntityAssembler;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.ResponseDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.dto.filter.AccountFilterDTO;
import it.fabrick.test.dto.filter.CreditorFilterDTO;
import it.fabrick.test.dto.filter.MoneyTransferFilterDTO;
import it.fabrick.test.dto.filter.NaturalPersonBeneficiaryFilterDTO;
import it.fabrick.test.dto.filter.TaxReliefFilterDTO;
import it.fabrick.test.entity.DAccount;
import it.fabrick.test.entity.DTransaction;
import it.fabrick.test.exception.RestClientException;
import it.fabrick.test.feignclient.OperazioniClient;
import it.fabrick.test.filter.OperationFilter;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.OperationModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.repository.AccountRepository;
import it.fabrick.test.repository.TransactionRepository;
import it.fabrick.test.service.OperazioniService;
import it.fabrick.test.utility.FeignExceptionHandler;

@Service
public class OperazioniServiceImpl implements OperazioniService {
	
	@Autowired
	protected CommonAssembler assembler;
	@Autowired
	protected EntityAssembler entityAssembler;
	@Autowired
	protected OperazioniClient operazioniClient;
	@Autowired
	protected TransactionRepository transactionRepository;
	@Autowired
	protected AccountRepository accountRepository;
	@Autowired
	protected FeignExceptionHandler feignHandler;
	
	@Override
	public BalanceModel getBalance(Long accountId) {
		BalanceModel output = null;
		ResponseDTO<BalanceDTO> result = null;
		try {
			result = operazioniClient.getBalance();
		} catch (FeignException fe) {
			throw feignHandler.buildRestClientException(fe);
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}
		if (result != null && result.getPayload() != null) {
			output = assembler.assembleBalance(result.getPayload(), accountId);
		}
		return output;
	}

	@Override
	public List<TransactionModel> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) {
		List<TransactionModel> output = null;
		ResponseDTO<ListOutputDTO<TransactionDTO>> result = null;
		try {
			result = operazioniClient.getTransactions(fromAccountingDate, toAccountingDate);
		} catch (FeignException fe) {
			throw feignHandler.buildRestClientException(fe);
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}
		if (result != null && result.getPayload() != null && !result.getPayload().getList().isEmpty()) {
			DAccount account = accountRepository.findById(accountId).orElse(null);
			if (account == null) {
				account = new DAccount();
				account.setAccountId(accountId);
				account.setTransactions(new ArrayList<>());
				accountRepository.saveAndFlush(account);
			}
			output = assembler.assembleTransactions(result.getPayload().getList(), accountId);
			// non ?? prevista la rimozione dei record dalla tabella perch?? non dovrebbe essere
			// possibile cancellare operazioni passate
			for (TransactionModel t : output) {
				if (!transactionRepository.existsById(t.getTransactionId())) {
					DTransaction transaction = entityAssembler.assembleTransaction(t);
					transaction.setAccount(account);
					transactionRepository.saveAndFlush(transaction);
					account.getTransactions().add(transaction);
					accountRepository.saveAndFlush(account);
				}
			}
		}
		return output;
	}
	
	@Override
	public OperationModel doMoneyTransfer(Long accountId, OperationFilter filter) {
		OperationModel output = null;
		MoneyTransferFilterDTO moneyFilter = new MoneyTransferFilterDTO();
		moneyFilter.setAmount(filter.getAmount());
		CreditorFilterDTO creditor = new CreditorFilterDTO();
		creditor.setName(filter.getReceiverName());
		AccountFilterDTO account = new AccountFilterDTO();
		account.setAccountCode(filter.getCreditorIBAN());
		creditor.setAccount(account);
		moneyFilter.setCreditor(creditor);
		moneyFilter.setCurrency(filter.getCurrency());
		moneyFilter.setDescription(filter.getDescription());
		moneyFilter.setExecutionDate(filter.getExecutionDate());
		TaxReliefFilterDTO taxRelief = new TaxReliefFilterDTO();
		taxRelief.setBeneficiaryType(filter.getBeneficiaryType());
		taxRelief.setCreditorFiscalCode(filter.getCreditorPIVAOrFiscalCode());
		taxRelief.setIsCondoUpgrade(filter.getCondoUpgrade());
		NaturalPersonBeneficiaryFilterDTO naturalPerson = new NaturalPersonBeneficiaryFilterDTO();
		naturalPerson.setFiscalCode1(filter.getCreditorFiscalCode());
		taxRelief.setNaturalPersonBeneficiary(naturalPerson);
		moneyFilter.setTaxRelief(taxRelief);
		ResponseDTO<MoneyTransferDTO> result = null;
		try {
			result = operazioniClient.doMoneyTransfer(moneyFilter);
		} catch (FeignException fe) {
			throw feignHandler.buildRestClientException(fe);
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}
		if (result != null && result.getPayload() != null) {
			output = assembler.assembleOperation(result.getPayload(), accountId);
		}
		return output;
	}
}
