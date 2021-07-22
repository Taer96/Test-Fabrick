package it.operazioni.ms.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EsitoListDTO <T> extends EsitoBaseDTO {

	private static final long serialVersionUID = 4334479001359920619L;
	private List<T> parameters;
	
	public EsitoListDTO(String status, Integer code, String description) {
		super(status, code, description);
	}
	
	public EsitoListDTO(String status, Integer code, String description, List<T> parameters) {
		super(status, code, description);
		this.parameters = parameters;
	}
}
