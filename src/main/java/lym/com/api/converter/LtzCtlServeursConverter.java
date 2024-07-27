package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlServeurs;

public class LtzCtlServeursConverter extends StdConverter<LtzCtlServeurs, LtzCtlServeurs> {

	@Override
	public LtzCtlServeurs convert(LtzCtlServeurs value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlServeurs result = new LtzCtlServeurs();
			result.setId(value.getId());
			
			return result;
		}
		return value;
	}

}
