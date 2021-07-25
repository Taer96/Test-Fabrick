package it.fabrick.test.feignclient.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import it.fabrick.test.constants.ErrorConstants;
import it.fabrick.test.constants.ValueConstants;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.ResponseDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.dto.filter.MoneyTransferFilterDTO;
import it.fabrick.test.exception.FallbackException;
import it.fabrick.test.feignclient.OperazioniClient;

@Component
public class OperazioniFallbackFactory implements FallbackFactory<OperazioniClient> {

	@Override
	public OperazioniClient create(Throwable cause) {

		if (cause instanceof FeignException) {
			FeignException fe = (FeignException) cause;
			return new OperazioniClient() {

				@Override
				public ResponseDTO<BalanceDTO> getBalance() {
					throw buildError(fe);
				}

				@Override
				public ResponseDTO<MoneyTransferDTO> doMoneyTransfer(MoneyTransferFilterDTO filter) {
					throw buildError(fe);
				}

				@Override
				public ResponseDTO<ListOutputDTO<TransactionDTO>> getTransactions(String fromAccountingDate, String toAccountingDate) {
					throw buildError(fe);
				}
			};
		} else {
			return new OperazioniClient() {

				@Override
				public ResponseDTO<BalanceDTO> getBalance() {
					throw new FallbackException(cause.getMessage());
				}

				@Override
				public ResponseDTO<MoneyTransferDTO> doMoneyTransfer(MoneyTransferFilterDTO filter) {
					throw new FallbackException(cause.getMessage());
				}

				@Override
				public ResponseDTO<ListOutputDTO<TransactionDTO>> getTransactions(String fromAccountingDate, String toAccountingDate) {
					throw new FallbackException(cause.getMessage());
				}
			};
		}
	}
	
	@SuppressWarnings("unchecked")
	private FallbackException buildError(FeignException fe) {
		ResponseDTO<String> response = null;
		try {
			response = new ObjectMapper().readValue(fe.contentUTF8(), ResponseDTO.class);
		} catch (Exception e) {
			return new FallbackException(fe.status(), fe.getMessage(), ErrorConstants.MISSING_ERROR);
		}
		List<String> errorList = response.getErrors().stream().map(e -> e.getDescription()).collect(Collectors.toList());
		return new FallbackException(fe.status(), fe.getMessage(), String.join(ValueConstants.NEW_LINE, errorList));
	}
}
