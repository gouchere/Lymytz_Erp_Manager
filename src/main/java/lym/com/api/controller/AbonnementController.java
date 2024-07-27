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

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlAbonnementsAssistances;
import lym.com.api.model.base.LtzCtlAbonnementsRemises;
import lym.com.api.model.base.LtzCtlAbonnementsServices;
import lym.com.api.service.IAbonnements;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/abonnements")
public class AbonnementController {

	public AbonnementController() {
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	private Ressourceservice service;
	@Autowired
	IAbonnements service;

	@GetMapping("/list")
	public List<LtzCtlAbonnements> getAbonnements() {
		return service.findAllAbonnements();
	}

	@GetMapping("/abonnement/{id}")
	public LtzCtlAbonnements getAbonnements(@PathVariable("id") long id) {
		Optional<LtzCtlAbonnements> re = service.getOneAbonnement(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@GetMapping("/one_by_customer/{customer}")
	public LtzCtlAbonnements getAbonnementByCustomer(@PathVariable("customer") long customer) {
		Optional<LtzCtlAbonnements> re = service.findByCustomer(customer);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlAbonnements> saveAbonnement(@RequestBody LtzCtlAbonnements entity) {
		return service.saveOneAbonnement(entity);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlAbonnements> updateAbonnement(@RequestBody LtzCtlAbonnements entity) {
		return service.updateOneAbonnement(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlAbonnements> updateAbonnements(@RequestBody List<LtzCtlAbonnements> listEntity) {
		return service.updateAbonnements(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlAbonnements> deleteAbonnement(@RequestBody LtzCtlAbonnements entity) {
		return service.deleteOnAbonnement(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlAbonnements> deleteAbonnements(@RequestBody List<LtzCtlAbonnements> entity) {
		return service.deleteAbonnements(entity);
	}

	@PostMapping("/add_service")
	@Transactional
	public ResultAction<LtzCtlAbonnementsServices> addService(@RequestBody LtzCtlAbonnementsServices service) {
		return this.service.addService(service);
	}

	@PostMapping("/remove_service")
	@Transactional
	public ResultAction<LtzCtlAbonnementsServices> removeService(@RequestBody LtzCtlAbonnementsServices service) {
		return this.service.removeService(service);
	}

	@PostMapping("/add_assistance")
	@Transactional
	public ResultAction<LtzCtlAbonnementsAssistances> addAssistance(@RequestBody LtzCtlAbonnementsAssistances assistance) {
		return this.service.addAssistance(assistance);
	}

	@PostMapping("/remove_assistance")
	@Transactional
	public ResultAction<LtzCtlAbonnementsAssistances> removeAssistance(@RequestBody LtzCtlAbonnementsAssistances assistance) {
		return this.service.removeAssistance(assistance);
	}

	@PostMapping("/add_remise")
	@Transactional
	public ResultAction<LtzCtlAbonnementsRemises> addRemise(@RequestBody LtzCtlAbonnementsRemises remise) {
		return this.service.addRemise(remise);
	}

	@PostMapping("/remove_remise")
	@Transactional
	public ResultAction<LtzCtlAbonnementsRemises> removeRemise(@RequestBody LtzCtlAbonnementsRemises remise) {
		return this.service.removeRemise(remise);
	}
}
