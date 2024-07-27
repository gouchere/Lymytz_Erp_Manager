package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlUsers;

public class LtzCtlUsersConverter extends StdConverter<LtzCtlUsers, LtzCtlUsers> {

	@Override
	public LtzCtlUsers convert(LtzCtlUsers value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlUsers result = new LtzCtlUsers();
			result.setId(value.getId());
			result.setNom(value.getNom());
			result.setPrenom(value.getPrenom());
			result.setEmail(value.getEmail()); 
			return result;
		}
		return new LtzCtlUsers();
	}

}
