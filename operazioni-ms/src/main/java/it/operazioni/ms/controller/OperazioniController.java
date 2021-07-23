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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.fabrick.test.constants.DateConstants;
import it.fabrick.test.constants.EndpointConstants;
import it.fabrick.test.constants.ResponseConstants;
import it.fabrick.test.exception.FallbackException;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.service.OperazioniService;
import it.operazioni.ms.assembler.OperazioniAssembler;
import it.operazioni.ms.dto.BalanceOutDTO;
import it.operazioni.ms.dto.EsitoOutDTO;
import it.operazioni.ms.dto.EsitoListOutDTO;
import it.operazioni.ms.dto.TransactionOutDTO;

@RestController
@RequestMapping(EndpointConstants.BASE_URL)
public class OperazioniController {
	
	@Value("${credenziali.accountId}")
	private String validId;
	@Autowired
	private OperazioniService operazioniService;
	@Autowired
	private OperazioniAssembler operazioniAssembler;

	/**
	 * controller per ottenere il saldo di un account
	 */
	@CrossOrigin
	@GetMapping(value = EndpointConstants.SALDO, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EsitoOutDTO<BalanceOutDTO>> getSaldo(@PathVariable Long accountId) {
		// non controllo la presenza di accountId, perchè è un parametro required
		if (!accountId.toString().equals(validId)) {
			return new ResponseEntity<>(new EsitoOutDTO<>(ResponseConstants.KO, HttpStatus.FORBIDDEN.value(), 
					ResponseConstants.ACCOUNT_PROBLEMS), HttpStatus.FORBIDDEN);
		}
		BalanceModel balance = null;
		try {
			balance = operazioniService.getBalance(accountId);
		} catch (FallbackException e) {
			return new ResponseEntity<>(new EsitoOutDTO<>(ResponseConstants.KO, e.getCode(), e.getMessage()), HttpStatus.valueOf(e.getCode()));
		}
		if (balance != null) {
			return new ResponseEntity<>(new EsitoOutDTO<>(ResponseConstants.OK, HttpStatus.OK.value(), ResponseConstants.ACCOUNT_TROVATO,
					operazioniAssembler.assembleBalance(balance)), HttpStatus.OK);
		}
		return null;
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
			return new ResponseEntity<>(new EsitoListOutDTO<>(ResponseConstants.KO, HttpStatus.FORBIDDEN.value(),
					ResponseConstants.ACCOUNT_PROBLEMS), HttpStatus.FORBIDDEN);
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(DateConstants.PATTERN);
			format.parse(fromAccountingDate);
			format.parse(toAccountingDate);
		} catch (ParseException e) {
			return new ResponseEntity<>(new EsitoListOutDTO<>(ResponseConstants.KO, HttpStatus.BAD_REQUEST.value(),
					ResponseConstants.WRONG_PATTERN), HttpStatus.BAD_REQUEST);
		}
		List<TransactionModel> transactions = null;
		try {
			transactions = operazioniService.getTransactions(accountId, fromAccountingDate, toAccountingDate);
		} catch (FallbackException e) {
			return new ResponseEntity<>(new EsitoListOutDTO<>(ResponseConstants.KO, e.getCode(), e.getMessage()),
					HttpStatus.valueOf(e.getCode()));
		}
		if (transactions != null) {
			return new ResponseEntity<>(new EsitoListOutDTO<>(ResponseConstants.OK, HttpStatus.OK.value(), ResponseConstants.ACCOUNT_TROVATO, 
					operazioniAssembler.assembleTransactions(transactions)), HttpStatus.OK);
		}
		return null;
	}
}
