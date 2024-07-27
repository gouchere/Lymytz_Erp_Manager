package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlNiveaux;

public class LtzCtlNiveauConverter extends StdConverter<LtzCtlNiveaux, LtzCtlNiveaux> {

	@Override
	public LtzCtlNiveaux convert(LtzCtlNiveaux value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlNiveaux result = new LtzCtlNiveaux();
			result.setId(value.getId());
			return result;
		}
		return value;
	}

}
