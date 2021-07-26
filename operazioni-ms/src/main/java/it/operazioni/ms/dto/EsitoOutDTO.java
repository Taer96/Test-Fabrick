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
	
	public EsitoOutDTO(String code, String description) {
		super(code, description);
	}
	
	public EsitoOutDTO(String code, String description, T payload) {
		super(code, description);
		this.payload = payload;
	}
}
