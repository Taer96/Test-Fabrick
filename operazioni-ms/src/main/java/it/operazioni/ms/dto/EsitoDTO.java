package it.operazioni.ms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EsitoDTO <T> extends EsitoBaseDTO {

	private static final long serialVersionUID = 2460351824664843001L;
	private T parameter;
	
	public EsitoDTO(String status, Integer code, String description) {
		super(status, code, description);
	}
	
	public EsitoDTO(String status, Integer code, String description, T parameter) {
		super(status, code, description);
		this.parameter = parameter;
	}
}
