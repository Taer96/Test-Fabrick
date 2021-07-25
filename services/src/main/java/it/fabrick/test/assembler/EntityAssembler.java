package it.fabrick.test.assembler;

import org.springframework.stereotype.Component;

import it.fabrick.test.entity.DTransaction;
import it.fabrick.test.model.TransactionModel;

@Component
public class EntityAssembler {

	public DTransaction assembleTransaction(TransactionModel t) {
		DTransaction output = new DTransaction();
		if (t != null) {
			output.setAccountingDate(t.getAccountingDate());
			output.setAmount(t.getAmount());
			output.setCurrency(t.getCurrency().toString());
			output.setDescription(t.getDescription());
			output.setOperationId(t.getOperationId());
			output.setTransactionId(t.getTransactionId());
			output.setValueDate(t.getValueDate());
		}
		return output;
	}
}