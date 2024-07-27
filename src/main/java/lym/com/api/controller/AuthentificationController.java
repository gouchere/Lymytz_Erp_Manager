package lym.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.service.authentification.IServiceAuthentification;
import lym.com.api.service.commons.ResultAction;

@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/authentification")
public class AuthentificationController {
	
	@Autowired
	private IServiceAuthentification service;
	
	@GetMapping("/connect")
	public ResultAction<LtzCtlUsers> connect(@RequestHeader("loggin") String loggin, @RequestHeader("password") String password) {
		return service.authentification(loggin, password);
	}
}
