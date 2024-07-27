package lym.com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lym.com.api.model.base.LtzCtlRemises;  
import lym.com.api.service.IRemises;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/remises")
public class RemisesController {

	public RemisesController() {
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	private Ressourceservice service;
	@Autowired
	IRemises service;

	@GetMapping("/list")
	public List<LtzCtlRemises> getRemises() {
		return service.findAllRemises();
	}

	@GetMapping("/remise/{id}")
	public LtzCtlRemises getRemises(@PathVariable("id") long id) {
		Optional<LtzCtlRemises> re = service.getOneRemise(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlRemises> saveRemise(@RequestBody LtzCtlRemises entity) {
		return service.saveOneRemise(entity);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlRemises> updateRemise(@RequestBody LtzCtlRemises entity) {
		return service.updateOneRemise(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlRemises> updateRemises(@RequestBody List<LtzCtlRemises> listEntity) {
		return service.updateRemises(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlRemises> deleteRemise(@RequestBody LtzCtlRemises entity) {
		return service.deleteOnRemise(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlRemises> deleteRemises(@RequestBody List<LtzCtlRemises> entity) {
		return service.deleteRemises(entity);
	}
}
