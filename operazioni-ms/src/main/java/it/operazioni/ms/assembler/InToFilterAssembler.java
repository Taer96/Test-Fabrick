package it.operazioni.ms.assembler;

import org.springframework.stereotype.Component;

import it.fabrick.test.filter.OperationFilter;
import it.operazioni.ms.constants.MockConstants;
import it.operazioni.ms.dto.filter.OperationInDTO;

@Component
public class InToFilterAssembler {

	// mancano sicuramente dei campi
	public OperationFilter assembleOperationFilter(OperationInDTO op) {
		OperationFilter output = new OperationFilter();
		if (op != null) {
			output.setAmount(op.getAmount());
			output.setBeneficiaryType(MockConstants.BENEFICIARY_TYPE);
			output.setCondoUpgrade(MockConstants.IS_CONDO_UPGRADE);
			output.setCreditorFiscalCode(MockConstants.NATURAL_PERSON_FISCAL_CODE);
			output.setCreditorIBAN(MockConstants.CREDITOR_IBAN);
			output.setCreditorPIVAOrFiscalCode(MockConstants.CREDITOR_FISCAL_CODE);
			output.setCurrency(op.getCurrency());
			output.setDescription(op.getDescription());
			output.setExecutionDate(op.getExecutionDate());
			output.setReceiverName(op.getReceiverName());
		}
		return output;
	}
}
