package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlServices;

public class LtzCtlServiceConverter extends StdConverter<LtzCtlServices, LtzCtlServices> {

	@Override
	public LtzCtlServices convert(LtzCtlServices value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlServices result = new LtzCtlServices();
			result.setId(value.getId());
			result.setCodeService(value.getCodeService());
			result.setCategorie(value.getCategorie());
			result.setDesignation(value.getDesignation());
			result.setPrix(value.getPrix());
			return result;
		}else {
			return new LtzCtlServices();
		}
		
	}

}
