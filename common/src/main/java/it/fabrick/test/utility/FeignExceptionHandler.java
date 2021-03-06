package it.fabrick.test.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import it.fabrick.test.constants.ErrorConstants;
import it.fabrick.test.constants.ValueConstants;
import it.fabrick.test.dto.ResponseDTO;
import it.fabrick.test.exception.RestClientException;

@Component
public class FeignExceptionHandler {

	@SuppressWarnings("unchecked")
	public RestClientException buildRestClientException(FeignException fe) {
		ResponseDTO<String> response = null;
		try {
			response = new ObjectMapper().readValue(fe.contentUTF8(), ResponseDTO.class);
		} catch (Exception e) {
			return new RestClientException(ErrorConstants.MISSING_ERROR, fe.getMessage(), ErrorConstants.MISSING_ERROR, fe.status());
		}
		List<String> errorList = response.getErrors().stream().map(e -> e.getDescription()).collect(Collectors.toList());
		List<String> statusList = response.getErrors().stream().map(e -> e.getCode()).collect(Collectors.toList());
		return new RestClientException(fe.getMessage(), String.join(ValueConstants.NEW_LINE, statusList),
				String.join(ValueConstants.NEW_LINE, errorList), fe.status());
	}
}
