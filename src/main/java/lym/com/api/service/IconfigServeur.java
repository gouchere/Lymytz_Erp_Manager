package lym.com.api.service;

import java.util.Properties;

import lym.com.api.service.commons.ResultAction;

public interface IconfigServeur {

	public Properties getConfigFile();
	
	public ResultAction<Properties> writeConfigFile(Properties file);
}
