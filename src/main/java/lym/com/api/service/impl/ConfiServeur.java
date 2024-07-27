package lym.com.api.service.impl;

import java.util.Properties;

import org.springframework.stereotype.Service;

import lym.com.api.Initialisation;
import lym.com.api.service.IconfigServeur;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
public class ConfiServeur implements IconfigServeur {

	@Override
	public Properties getConfigFile() {
		return Initialisation.readFileServeurPropertie();
	}

	@Override
	public ResultAction<Properties> writeConfigFile(Properties file) {
		ResultAction<Properties> re = new ResultAction<>(true);
		if (file == null) {
			re = new ResultAction<>(false, -1, "Object IS NULL", "L'objet de Configuration est null");
		} else if (!ConstantesManager.isString(file.get("id_societe").toString())) {
			re = new ResultAction<>(false, -1, "Propertie id_societe IS NULL", "L'objet de Configuration est null");
		} else if (!ConstantesManager.isString(file.get("port_web").toString())) {
			re = new ResultAction<>(false, -1, "Propertie port_web IS NULL", "L'objet de Configuration est null");
		} else if (!ConstantesManager.isString(file.get("adresse_web").toString())) {
			re = new ResultAction<>(false, -1, "Propertie adresse_web IS NULL", "L'objet de Configuration est null");
		} else if (!ConstantesManager.isString(file.get("code_user").toString())) {
			re = new ResultAction<>(false, -1, "Propertie code_user IS NULL", "L'objet de Configuration est null");
		} else
			Initialisation.writeInFile(file);
		return re;
	}

}
