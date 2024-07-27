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

import lym.com.api.model.base.LtzCtlAutorisations;
import lym.com.api.model.base.LtzCtlCustomerNiveaux;
import lym.com.api.model.base.LtzCtlNiveaux;
import lym.com.api.service.INiveaux;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/niveaux")
public class NiveauxController {

	public NiveauxController() {
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	private Niveauxervice service;
	@Autowired
	INiveaux service;

	@GetMapping("/list")
	public List<LtzCtlNiveaux> getServeurs() {
		return service.findAllNiveaux();
	}

	@GetMapping("/niveau/{id}")
	public LtzCtlNiveaux getServeurs(@PathVariable("id") long id) {
		Optional<LtzCtlNiveaux> re = service.getOneNiveau(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@GetMapping("/list_actif")
	public List<LtzCtlNiveaux> getNiveaux(Boolean actif) {
		return service.findAllNiveauActif(actif);
	}
	
	@GetMapping("/list/{publique}")
	public List<LtzCtlNiveaux> getNiveauxByPublic(@PathVariable("publique") Boolean publique) {
		return service.findAllNiveauByPublic(publique);
	}

	@GetMapping("/defaut")
	public LtzCtlNiveaux getDefaut() {
		Optional<LtzCtlNiveaux> re = service.findDefautNiveau();
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlNiveaux> saveServeur(@RequestBody LtzCtlNiveaux entity) {
		return service.saveOneNiveau(entity);
	}

	@PostMapping("/save_customer_niveau")
	@Transactional
	public ResultAction<LtzCtlCustomerNiveaux> saveCustomerNiveau(@RequestBody LtzCtlCustomerNiveaux entity) {
		return service.saveOneCustomerNiveau(entity);
	}

	@GetMapping("/autorisations/{id}")
	public List<LtzCtlAutorisations> findAutorisationsNiveau(@PathVariable("id") Long idNiveau) {
		return service.loadAutorisationsByNiveau(idNiveau);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlNiveaux> updateServeur(@RequestBody LtzCtlNiveaux entity) {
		return service.updateOneNiveau(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlNiveaux> updateServeurs(@RequestBody List<LtzCtlNiveaux> listEntity) {
		return service.updateNiveaux(listEntity);
	}

	@PostMapping("/save_autorisation")
	@Transactional
	public ResultAction<LtzCtlAutorisations> addAutorisation(@RequestBody LtzCtlAutorisations entity) {
		return service.addAutorisation(entity);
	}

	@PostMapping("/save_many_autorisation")
	@Transactional
	public ResultAction<LtzCtlAutorisations> addAutorisation(@RequestBody List<LtzCtlAutorisations> listEntity) {
		return service.addAutorisation(listEntity);
	}

	@PostMapping("/toogle_active_autorisation")
	@Transactional
	public ResultAction<LtzCtlAutorisations> toogleActiveAutorisation(@RequestBody LtzCtlAutorisations entity) {
		return service.toogleAutorisation(entity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlNiveaux> deletServeur(@RequestBody LtzCtlNiveaux entity) {
		return service.deleteOnNiveau(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlNiveaux> deletServeur(@RequestBody List<LtzCtlNiveaux> entity) {
		return service.deleteNiveaux(entity);
	}
}
