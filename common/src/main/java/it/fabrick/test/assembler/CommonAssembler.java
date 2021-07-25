package it.fabrick.test.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fabrick.test.dto.AmountDTO;
import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.dto.MoneyTransferDTO;
import it.fabrick.test.dto.TransactionDTO;
import it.fabrick.test.dto.ValueDTO;
import it.fabrick.test.model.BalanceModel;
import it.fabrick.test.model.OperationModel;
import it.fabrick.test.model.TransactionModel;
import it.fabrick.test.model.ValueModel;
import it.fabrick.test.utility.StringUtility;

@Component
public class CommonAssembler {
	
	@Autowired
	StringUtility stringUtility;

	public BalanceModel assembleBalance(BalanceDTO balance, Long accountId) {
		BalanceModel output = new BalanceModel();
		output.setAccountId(accountId);
		if (balance != null) {
			output.setAvailableBalance(balance.getAvailableBalance());
			output.setBalance(balance.getBalance());
			output.setCurrency(balance.getCurrency());
			output.setDate(balance.getDate());
		}
		return output;
	}

	public List<TransactionModel> assembleTransactions(List<TransactionDTO> list, Long accountId) {
		List<TransactionModel> output = new ArrayList<>();
		if (list != null) {
			for (TransactionDTO t : list) {
				TransactionModel element = new TransactionModel();
				element.setAccountId(accountId);
				element.setAccountingDate(t.getAccountingDate());
				element.setAmount(t.getAmount());
				element.setCurrency(t.getCurrency());
				element.setDescription(stringUtility.multipleSpacesToDash(t.getDescription()));
				element.setOperationId(t.getOperationId());
				element.setTransactionId(t.getTransactionId());
				element.setType(assembleValue(t.getType()));
				element.setValueDate(t.getValueDate());
				output.add(element);
			}
		}
		return output;
	}

	private ValueModel assembleValue(ValueDTO value) {
		ValueModel output = new ValueModel();
		if (value != null) {
			output.setEnumeration(value.getEnumeration());
			output.setValue(value.getValue());
		}
		return output;
	}

	public OperationModel assembleOperation(MoneyTransferDTO transfer, Long accountId) {
		OperationModel output = new OperationModel();
		output.setAccountId(accountId);
		if (transfer != null) {
			AmountDTO amount = transfer.getAmount();
			output.setAmountSent(amount.getDebtorAmount());
			output.setCurrencyUsed(amount.getDebtorCurrency().toString());
			output.setDirection(transfer.getDirection());
			output.setFeesAmount(transfer.getFees().stream().mapToDouble(f -> f.getAmount()).sum());
		}
		return output;
	}
}
