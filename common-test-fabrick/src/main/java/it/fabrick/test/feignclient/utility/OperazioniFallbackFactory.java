package it.fabrick.test.feignclient.utility;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import feign.FeignException;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.ListOutputDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.ResponseErrorDTO;
import it.fabrick.test.dto.ResponseErrorsDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.dto.filter.MoneyTransferFilterDTO;
import it.fabrick.test.exception.FallbackException;
import it.fabrick.test.feignclient.OperazioniClient;

@Component
public class OperazioniFallbackFactory implements FallbackFactory<OperazioniClient> {

	@Override
	public OperazioniClient create(Throwable cause) {

		Integer httpStatus = cause instanceof FeignException ? ((FeignException) cause).status() : null;

		return new OperazioniClient() {

			@Override
			public ResponseErrorDTO<BalanceDTO> getSaldoAccount() {
				throw new FallbackException(httpStatus, cause.getMessage());
			}

			@Override
			public ResponseErrorsDTO<MoneyTransferDTO> trasferisciSoldi(MoneyTransferFilterDTO filter) {
				throw new FallbackException(httpStatus, cause.getMessage());
			}

			@Override
			public ResponseErrorDTO<ListOutputDTO<TransactionDTO>> getTransazioni(String fromAccountingDate, String toAccountingDate) {
				throw new FallbackException(httpStatus, cause.getMessage());
			}
		};
	}
}
