package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlTickets;

public class LtzCtlTicketsConverter extends StdConverter<LtzCtlTickets, LtzCtlTickets> {

	@Override
	public LtzCtlTickets convert(LtzCtlTickets value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlTickets result = new LtzCtlTickets();
			result.setId(value.getId());
			result.setObjet(value.getObjet());
			result.setMessage(value.getMessage());
			return result;
		}
		return new LtzCtlTickets();
	}

}
