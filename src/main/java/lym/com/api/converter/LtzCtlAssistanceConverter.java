package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlAssistances;

public class LtzCtlAssistanceConverter extends StdConverter<LtzCtlAssistances, LtzCtlAssistances> {

	@Override
	public LtzCtlAssistances convert(LtzCtlAssistances value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlAssistances result = new LtzCtlAssistances();
			result.setId(value.getId());
			result.setDesignation(value.getDesignation());
			result.setPrix(value.getPrix());
			result.setCodeAssistance(value.getCodeAssistance());
			return result;
		}else {
			return new LtzCtlAssistances();
		}
		
	}

}
