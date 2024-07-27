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

import lym.com.api.model.base.LtzCtlAssistances;
import lym.com.api.service.IAssistances;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/assistances")
public class AssistancesController {

	public AssistancesController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	IAssistances service;

	@GetMapping("/list")
	public List<LtzCtlAssistances> getperiodes() {
		return service.findAllAssistances();
	}

	@GetMapping("/assistance/{id}")
	public LtzCtlAssistances getperiodes(@PathVariable("id") long id) {
		Optional<LtzCtlAssistances> re = service.getOneAssistance(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlAssistances> saveperiode(@RequestBody LtzCtlAssistances entity) {
		return service.saveOneAssistance(entity);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlAssistances> updateperiode(@RequestBody LtzCtlAssistances entity) {
		return service.updateOneAssistance(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlAssistances> updateperiodes(@RequestBody List<LtzCtlAssistances> listEntity) {
		return service.updateAssistances(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlAssistances> deleteperiode(@RequestBody LtzCtlAssistances entity) {
		return service.deleteOneAssistance(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlAssistances> deleteperiodes(@RequestBody List<LtzCtlAssistances> entity) {
		return service.deleteAssistances(entity);
	}
}
