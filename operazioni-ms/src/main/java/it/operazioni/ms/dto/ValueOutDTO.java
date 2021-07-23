package it.operazioni.ms.dto;

import java.io.Serializable;

import it.fabrick.test.utility.ValueTypeEnum;
import lombok.Data;

@Data
public class ValueOutDTO implements Serializable {

	private static final long serialVersionUID = -5080534994411568534L;
	private ValueTypeEnum enumeration;
	private String value;
}
