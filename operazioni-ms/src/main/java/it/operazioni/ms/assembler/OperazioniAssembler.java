package it.operazioni.ms.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.OperationModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.model.ValueModel;
import it.operazioni.ms.dto.BalanceOutDTO;
import it.operazioni.ms.dto.OperationOutDTO;
import it.operazioni.ms.dto.TransactionOutDTO;
import it.operazioni.ms.dto.ValueOutDTO;

@Component
public class OperazioniAssembler {

	public BalanceOutDTO assembleBalance(BalanceModel balance) {
		BalanceOutDTO output = new BalanceOutDTO();
		if (balance != null)  {
			output.setAccountId(balance.getAccountId());
			output.setAvailableBalance(balance.getAvailableBalance());
			output.setBalance(balance.getBalance());
			output.setCurrency(balance.getCurrency());
			output.setDate(balance.getDate());
		}
		return output;
	}

	public List<TransactionOutDTO> assembleTransactions(List<TransactionModel> transactions) {
		List<TransactionOutDTO> output = new ArrayList<>();
		if (transactions != null) {
			for (TransactionModel t : transactions) {
				TransactionOutDTO element = new TransactionOutDTO();
				element.setAccountId(t.getAccountId());
				element.setAccountingDate(t.getAccountingDate());
				element.setAmount(t.getAmount());
				element.setCurrency(t.getCurrency());
				element.setDescription(t.getDescription());
				element.setOperationId(t.getOperationId());
				element.setTransactionId(t.getTransactionId());
				element.setType(assembleValue(t.getType()));
				element.setValueDate(t.getValueDate());
				output.add(element);
			}
		}
		return output;
	}
	
	private ValueOutDTO assembleValue(ValueModel value) {
		ValueOutDTO output = new ValueOutDTO();
		if (value != null) {
			output.setEnumeration(value.getEnumeration());
			output.setValue(value.getValue());
		}
		return output;
	}

	public OperationOutDTO assembleOperation(OperationModel op) {
		OperationOutDTO output = new OperationOutDTO();
		if (op != null) {
			output.setAccountId(op.getAccountId());
			output.setAmount(op.getAmountSent());
			output.setCurrencyUsed(op.getCurrencyUsed());
			output.setDirection(op.getDirection());
			output.setFeesAmount(op.getFeesAmount());
			output.setOperationDate(op.getOperationDate());
			output.setReceiverIBAN(op.getReceiverIBAN());
			output.setReceiverName(op.getReceiverName());
			output.setStatus(op.getStatus());
			output.setTotalSpent(op.getTotalSpent());
		}
		return output;
	}

}
