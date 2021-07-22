package it.fabrick.test.assembler;

import org.springframework.stereotype.Component;

import it.fabrick.test.dto.BalanceDTO;
import it.fabrick.test.model.BalanceModel;

@Component
public class CommonAssembler {

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
}
