package lym.com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lym.com.api.model.base.LtzCtlLiaisonService;
import lym.com.api.model.base.LtzCtlServices;
import lym.com.api.service.IServices;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/services")
public class ServicesController {

	@Autowired
	private IServices service;

	@GetMapping("/list")
	public List<LtzCtlServices> getServices() {
		return service.findAll();
	}

	@GetMapping("/services_actif")
	public List<LtzCtlServices> getServices(Boolean actif) {
		return service.findAllActif(actif);
	}
	
	@GetMapping("/services_parent/{parent}")
	public List<LtzCtlServices> getServicesWithParent(@PathVariable("parent") long parent) {
		return service.findServiceWithParent(parent);
	}
	
	@GetMapping("/services_principal/{parent}")
	public List<LtzCtlLiaisonService> findByServicePrincipal(@PathVariable("parent") long parent) {
		return service.findByServicePrincipal(new LtzCtlServices(parent));
	}

	@GetMapping("/service/{id}")
	public LtzCtlServices getService(@PathVariable("id") long id) {
		Optional<LtzCtlServices> re = service.getOneService(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	public ResultAction<LtzCtlServices> saveService(@RequestBody LtzCtlServices entity) {
		return service.saveServiceWithSubServices(entity);
	}

	@PostMapping("/save_with_children")
	public ResultAction<LtzCtlServices> saveServiceWithChildren(@RequestBody LtzCtlServices entity) {
		return service.saveServiceWithSubServices(entity);
	}

	@PostMapping("/update")
	public ResultAction<LtzCtlServices> updateService(@RequestBody LtzCtlServices entity) {
		return service.updateOneService(entity);
	}
	
	@PostMapping("/update_many")
	public ResultAction<LtzCtlServices> updateManyService(@RequestBody List<LtzCtlServices> entity) {
		return service.updateServices(entity);
	}

	@PostMapping("/add")
	public ResultAction<LtzCtlLiaisonService> updateService(@RequestBody LtzCtlLiaisonService entity) {
		return service.addSubService(entity);
	}
	
	@PostMapping("/remove")
	public ResultAction<LtzCtlLiaisonService> removeService(@RequestBody LtzCtlLiaisonService entity) {
		return service.removeLiasonService(entity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlServices> deleteService(@RequestBody LtzCtlServices entity) {
		return service.deleteOneService(entity);
	}
	
	@PostMapping("/delete_many")
	public ResultAction<LtzCtlServices> deleteManyService(@RequestBody List<LtzCtlServices> entity) {
		return service.deleteServices(entity);
	}
}
