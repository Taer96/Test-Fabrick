package it.fabrick.test.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DebtorDTO extends CustomerDTO {

	private static final long serialVersionUID = -3467400971953628025L;
}
