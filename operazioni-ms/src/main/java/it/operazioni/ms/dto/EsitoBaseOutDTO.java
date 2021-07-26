package it.operazioni.ms.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
abstract class EsitoBaseOutDTO implements Serializable {

	private static final long serialVersionUID = 661246820014210843L;
	private String code; 
	private String description;
	
	public EsitoBaseOutDTO(String code, String description) {
		this.code = code;
		this.description = description;
	}
}
