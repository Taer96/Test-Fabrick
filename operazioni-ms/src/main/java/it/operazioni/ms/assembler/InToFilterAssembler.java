package it.operazioni.ms.assembler;

import org.springframework.stereotype.Component;

import it.fabrick.test.filter.OperationFilter;
import it.operazioni.ms.dto.filter.OperationInDTO;

@Component
public class InToFilterAssembler {

	// mancano sicuramente dei campi
	public OperationFilter assembleOperationFilter(OperationInDTO op) {
		OperationFilter output = new OperationFilter();
		if (op != null) {
			output.setAmount(op.getAmount());
			output.setCurrency(op.getCurrency());
			output.setDescription(op.getDescription());
			output.setExecutionDate(op.getExecutionDate());
			output.setReceiverName(op.getReceiverName());
		}
		return output;
	}
}
