package it.fabrick.test.model;

import java.io.Serializable;

import it.fabrick.test.utility.ValueTypeEnum;
import lombok.Data;

@Data
public class ValueModel implements Serializable {

	private static final long serialVersionUID = -5182665877077188005L;
	private ValueTypeEnum enumeration;
	private String value;
}
