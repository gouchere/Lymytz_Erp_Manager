package lym.com.api.service.authentification;

import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.service.commons.ResultAction;

public interface IServiceAuthentification {
	
	public ResultAction<LtzCtlUsers> authentification(String login, String password);


}
