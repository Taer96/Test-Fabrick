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
	
	public EsitoListOutDTO(String code, String description) {
		super(code, description);
	}
	
	public EsitoListOutDTO(String code, String description, List<T> payload) {
		super(code, description);
		this.payload = payload;
	}
}
