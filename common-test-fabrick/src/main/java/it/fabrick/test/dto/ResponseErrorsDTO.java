package it.fabrick.test.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseErrorsDTO <T> extends ResponseDTO<T> {

	private static final long serialVersionUID = 3444718126646043807L;
	private List<ErrorDTO> errors;
}
