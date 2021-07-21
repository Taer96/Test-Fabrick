package it.fabrick.test.dto;

import java.io.Serializable;

import it.fabrick.test.utility.ValueTypeEnum;
import lombok.Data;

@Data
public class ValueDTO implements Serializable {

	private static final long serialVersionUID = 7474343923817274036L;
	private ValueTypeEnum enumeration;
	private String value;
}
