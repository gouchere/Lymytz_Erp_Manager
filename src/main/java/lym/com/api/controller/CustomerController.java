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

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlCustomerServeur;
import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlServices;
import lym.com.api.service.ICustomer;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController()
@CrossOrigin("*")
@RequestMapping("erp_manager/customers")
public class CustomerController {

	@Autowired
	private ICustomer service;

	@GetMapping("/list")
	public List<LtzCtlCustomer> getCustomers() { 
		return service.findAllCustomer();
	}

	@GetMapping("/list_actif")
	public List<LtzCtlCustomer> getCustomers(Boolean actif) {
		return service.findAllCustomerActif(actif);
	}

	@GetMapping("/customer/{id}")
	public LtzCtlCustomer getCustomer(@PathVariable("id") long id) {
		Optional<LtzCtlCustomer> re = service.getOneCustomer(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@GetMapping("/services/{idCustomer}")
	public List<LtzCtlServices> findServiceByCustomer(@PathVariable("idCustomer") Long idCustomer) {
		return service.findByServiceByCustomerActif(new LtzCtlCustomer(idCustomer)); 
	}

	@PostMapping("/save")
	public ResultAction<LtzCtlCustomer> saveServeur(@RequestBody LtzCtlCustomer entity) {
		return service.saveOneCustomer(entity);
	}

	@PostMapping("/update")
	public ResultAction<LtzCtlCustomer> updateServeur(@RequestBody LtzCtlCustomer entity) {
		return service.updateOneCustomer(entity);
	}

	@PostMapping("/update_many")
	public ResultAction<LtzCtlCustomer> updateServeurs(@RequestBody List<LtzCtlCustomer> listEntity) {
		return service.updateCustomer(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlCustomer> deletServeur(@RequestBody LtzCtlCustomer entity) {
		ResultAction<LtzCtlCustomer> result = new ResultAction<>(true);
		try {
			result = service.deleteOneCustomer(entity);
		} catch (Exception e) {
			// TODO: handle exception
			result.setMessage(e.getMessage());
			result.setResult(false);
			result.setEntity(null);
			result.setCodeInfo(-1);
		}
		return result;
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlCustomer> deletServeur(@RequestBody List<LtzCtlCustomer> entity) {
		return service.deleteManyCustomer(entity);
	}

	@PostMapping("/add_compte")
	public ResultAction<LtzCtlCustomerUser> addCompte(@RequestBody LtzCtlCustomerUser entity) {
		return service.addCompte(entity);
	}

	/* Gérer la table customer serveur */
	@PostMapping("/add_serveur")
	public ResultAction<LtzCtlCustomerServeur> addServeur(@RequestBody LtzCtlCustomerServeur entity) {
		return service.addCustomerServeur(entity);
	}

	@PostMapping("/update_serveur")
	public ResultAction<LtzCtlCustomerServeur> updateServeur(@RequestBody LtzCtlCustomerServeur entity) {
		return service.updateCustomerServeur(entity);
	}
}
