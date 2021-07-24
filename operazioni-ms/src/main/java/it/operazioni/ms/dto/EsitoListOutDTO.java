package it.operazioni.ms.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EsitoListOutDTO <T> extends EsitoBaseOutDTO {

	private static final long serialVersionUID = 4334479001359920619L;
	private List<T> payload;
	
	public EsitoListOutDTO(String status, Integer code, String description) {
		super(status, code, description);
	}
	
	public EsitoListOutDTO(String status, Integer code, String description, List<T> payload) {
		super(status, code, description);
		this.payload = payload;
	}
}
