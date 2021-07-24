package it.operazioni.ms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EsitoOutDTO <T> extends EsitoBaseOutDTO {

	private static final long serialVersionUID = 2460351824664843001L;
	private T payload;
	
	public EsitoOutDTO(String status, Integer code, String description) {
		super(status, code, description);
	}
	
	public EsitoOutDTO(String status, Integer code, String description, T payload) {
		super(status, code, description);
		this.payload = payload;
	}
}
