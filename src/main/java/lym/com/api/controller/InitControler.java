package lym.com.api.controller;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.service.ICustomer;
import lym.com.api.service.IconfigServeur;
import lym.com.api.service.commons.ResultAction;

@RestController
@CrossOrigin("*") 
@RequestMapping("erp_manager")
public class InitControler {

	private static final Logger logger = LoggerFactory.getLogger(InitControler.class);
	@Autowired
	IconfigServeur service;
	@Autowired
	ICustomer serviceU;

	@GetMapping(value = "/")
	public ResponseEntity<String> pong() {
		logger.info("Démarrage des services OK .....");
		return new ResponseEntity<String>("Réponse du serveur: " + HttpStatus.OK.name(), HttpStatus.OK);
	}

	@GetMapping("/ping")
	public Boolean ping() {
		return true;
	}

	@GetMapping("/get_properties")
	public Properties getProperties() {
		return service.getConfigFile();
	}

	@PostMapping("/write_properties")
	public ResultAction<Properties> setProperties(@RequestBody Properties json) {
		service.writeConfigFile(json);
		return new ResultAction<>(true);
	}

	@GetMapping("/datasource")
	public List<LtzCtlCustomer> findAllSqlCustomer() {
		System.err.println("....");
		return serviceU.findAllSqlCustomer();
	}
}
