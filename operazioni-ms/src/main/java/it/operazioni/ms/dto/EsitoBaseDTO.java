package it.operazioni.ms.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
abstract class EsitoBaseDTO implements Serializable {

	private static final long serialVersionUID = 661246820014210843L;
	private String status;
	private Integer code; 
	private String description;
	
	public EsitoBaseDTO(String status, Integer code, String description) {
		this.status = status;
		this.code = code;
		this.description = description;
	}
}
