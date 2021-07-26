package it.operazioni.ms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.fabrick.test.constants.DateConstants;
import it.fabrick.test.constants.EndpointConstants;
import it.fabrick.test.constants.ResponseConstants;
import it.fabrick.test.exception.RestClientException;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.OperationModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.service.OperazioniService;
import it.operazioni.ms.assembler.InToFilterAssembler;
import it.operazioni.ms.assembler.OperazioniAssembler;
import it.operazioni.ms.dto.BalanceOutDTO;
import it.operazioni.ms.dto.EsitoListOutDTO;
import it.operazioni.ms.dto.EsitoOutDTO;
import it.operazioni.ms.dto.OperationOutDTO;
import it.operazioni.ms.dto.TransactionOutDTO;
import it.operazioni.ms.dto.filter.OperationInDTO;

@RestController
@RequestMapping(EndpointConstants.BASE_URL)
public class OperazioniController {

	@Value("${credenziali.accountId}")
	private String validId;
	@Autowired
	private OperazioniService operazioniService;
	@Autowired
	private OperazioniAssembler operazioniAssembler;
	@Autowired
	private InToFilterAssembler inToFilterAssembler;

	/**
	 * controller per ottenere il saldo di un account
	 */
	@CrossOrigin
	@GetMapping(value = EndpointConstants.SALDO, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EsitoOutDTO<BalanceOutDTO>> getSaldo(@PathVariable Long accountId) {
		// non controllo la presenza di accountId, perchè è un parametro required
		if (!accountId.toString().equals(validId)) {
			return new ResponseEntity<>(new EsitoOutDTO<>(HttpStatus.FORBIDDEN.toString(), 
					ResponseConstants.ACCOUNT_PROBLEMS), HttpStatus.FORBIDDEN);
		}
		BalanceModel balance = null;
		try {
			balance = operazioniService.getBalance(accountId);
		} catch (RestClientException e) {
			return new ResponseEntity<>(new EsitoOutDTO<>(e.getCode(), e.getErrors()),
					HttpStatus.valueOf(e.getStatusCode()));
		}
		if (balance != null) {
			return new ResponseEntity<>(new EsitoOutDTO<>(HttpStatus.OK.toString(), ResponseConstants.ACCOUNT_TROVATO,
					operazioniAssembler.assembleBalance(balance)), HttpStatus.OK);
		}
		// per arrivare qui, il sistema deve rispondere senza dati
		return new ResponseEntity<>(new EsitoOutDTO<>(HttpStatus.SERVICE_UNAVAILABLE.toString(),
				ResponseConstants.MISSING_DATA), HttpStatus.SERVICE_UNAVAILABLE);
	}

	/**
	 * controller per ottenere le transazioni di un account
	 */
	@CrossOrigin
	@GetMapping(value = EndpointConstants.TRANSAZIONI, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EsitoListOutDTO<TransactionOutDTO>> getTransazione(@PathVariable Long accountId, @RequestParam String fromAccountingDate, 
			@RequestParam String toAccountingDate) {
		// non controllo la presenza di accountId, perchè è un parametro required
		if (!accountId.toString().equals(validId)) {
			return new ResponseEntity<>(new EsitoListOutDTO<>(HttpStatus.FORBIDDEN.toString(),
					ResponseConstants.ACCOUNT_PROBLEMS), HttpStatus.FORBIDDEN);
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(DateConstants.PATTERN);
			format.parse(fromAccountingDate);
			format.parse(toAccountingDate);
		} catch (ParseException e) {
			return new ResponseEntity<>(new EsitoListOutDTO<>(HttpStatus.BAD_REQUEST.toString(),
					ResponseConstants.WRONG_PATTERN), HttpStatus.BAD_REQUEST);
		}
		List<TransactionModel> transactions = null;
		try {
			transactions = operazioniService.getTransactions(accountId, fromAccountingDate, toAccountingDate);
		} catch (RestClientException e) {
			return new ResponseEntity<>(new EsitoListOutDTO<>(e.getCode(), e.getErrors()),
					HttpStatus.valueOf(e.getStatusCode()));
		}
		if (transactions != null) {
			return new ResponseEntity<>(new EsitoListOutDTO<>(HttpStatus.OK.toString(), ResponseConstants.ACCOUNT_TROVATO, 
					operazioniAssembler.assembleTransactions(transactions)), HttpStatus.OK);
		}
		// per arrivare qui, il sistema deve rispondere senza dati
		return new ResponseEntity<>(new EsitoListOutDTO<>(HttpStatus.SERVICE_UNAVAILABLE.toString(),
				ResponseConstants.MISSING_DATA), HttpStatus.SERVICE_UNAVAILABLE);
	}

	/**
	 * controller per effettuare un bonifico
	 * NOTE: non funzionerà mai, mancano dei dati:
	 * creditor.account.accountCode
	 * taxRelief.isCondoUpgrade
	 * taxRelief.creditorFiscalCode
	 * taxRelief.beneficiaryType
	 * taxRelief.naturalPersonBeneficiary.fiscalCode1
	 */
	@CrossOrigin
	@PostMapping(value = EndpointConstants.MONEY_TRANSFER, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EsitoOutDTO<OperationOutDTO>> moneyTransfer(@PathVariable Long accountId, @RequestBody OperationInDTO operation) {
		// non controllo la presenza di accountId, perchè è un parametro required
		if (!accountId.toString().equals(validId)) {
			return new ResponseEntity<>(new EsitoOutDTO<>(HttpStatus.FORBIDDEN.toString(),
					ResponseConstants.ACCOUNT_PROBLEMS), HttpStatus.FORBIDDEN);
		}
		OperationModel op = null;
		try {
			op = operazioniService.doMoneyTransfer(accountId, inToFilterAssembler.assembleOperationFilter(operation));
		} catch (RestClientException e) {
			return new ResponseEntity<>(new EsitoOutDTO<>(e.getCode(), e.getErrors()),
					HttpStatus.valueOf(e.getStatusCode()));
		}
		if (op != null) {
			return new ResponseEntity<>(new EsitoOutDTO<>(HttpStatus.OK.toString(), ResponseConstants.ACCOUNT_TROVATO, 
					operazioniAssembler.assembleOperation(op)), HttpStatus.OK);
		}
		// per arrivare qui, il sistema deve rispondere senza dati
		return new ResponseEntity<>(new EsitoOutDTO<>(HttpStatus.SERVICE_UNAVAILABLE.toString(),
				ResponseConstants.MISSING_DATA), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
