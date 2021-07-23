package it.fabrick.test.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseErrorDTO <T> extends ResponseDTO<T> {

	private static final long serialVersionUID = -1687225740885201295L;
	private List<String> error;
}
