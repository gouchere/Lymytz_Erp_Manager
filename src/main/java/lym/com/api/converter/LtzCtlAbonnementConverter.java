package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlAbonnements;

public class LtzCtlAbonnementConverter extends StdConverter<LtzCtlAbonnements, LtzCtlAbonnements> {

	@Override
	public LtzCtlAbonnements convert(LtzCtlAbonnements value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlAbonnements result = new LtzCtlAbonnements();
			result.setId(value.getId());
			return result;
		}
		return value;
	}

}
