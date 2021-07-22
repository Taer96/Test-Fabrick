package it.operazioni.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.fabrick.test.constants.EndpointConstants;
import it.fabrick.test.constants.ResponseConstants;
import it.fabrick.test.service.OperazioniService;
import it.operazioni.ms.dto.EsitoDTO;
import it.operazioni.ms.dto.BalanceDTO;

@RestController
@RequestMapping(EndpointConstants.BASE_URL)
public class OperazioniController {
	
	@Value("${credenziali.accountId}")
	private String validId;
	@Autowired
	private OperazioniService operazioniService;

	/**
	 * controller per ottenere il saldo di un account
	 */
	@CrossOrigin
	@GetMapping(value = EndpointConstants.SALDO, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EsitoDTO<BalanceDTO>> getSaldo(@PathVariable Long accountId) {
		ResponseEntity<EsitoDTO<BalanceDTO>> output = null;
		// non controllo la presenza di accountId, perchè è un parametro required
		if (!accountId.toString().equals(validId)) {
			output = new ResponseEntity<>(new EsitoDTO<>(ResponseConstants.KO, HttpStatus.FORBIDDEN.value(),
					ResponseConstants.ACCOUNTPROBLEMS), HttpStatus.FORBIDDEN);
		}
		try {
			operazioniService.getBalance(accountId);
		} catch (Exception e) {
			
		}
		return output;
	}
}
