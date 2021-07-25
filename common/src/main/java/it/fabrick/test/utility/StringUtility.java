package it.fabrick.test.utility;

import org.springframework.stereotype.Component;

import it.fabrick.test.constants.ValueConstants;

@Component
public class StringUtility {

	public String multipleSpacesToDash(String input) {
		return input.trim().replaceAll("\\s{2,}", ValueConstants.DASH_WITH_SPACES);
	}
}
