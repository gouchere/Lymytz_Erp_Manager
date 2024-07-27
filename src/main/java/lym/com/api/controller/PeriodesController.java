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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lym.com.api.model.base.LtzCtlPeriodes;
import lym.com.api.service.IPeriodes;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/periodes")
public class PeriodesController {

	public PeriodesController() {
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	private Ressourceservice service;
	@Autowired
	IPeriodes service;

	@GetMapping("/list")
	public List<LtzCtlPeriodes> getperiodes() {
		return service.findAllPeriodes();
	}

	@GetMapping("/list_actif")
	public List<LtzCtlPeriodes> getPeriodesActif(@RequestHeader("actif") Boolean actif) {
		return service.findAllPeriodeActif(actif);
	}

	@GetMapping("/periode/{id}")
	public LtzCtlPeriodes getperiodes(@PathVariable("id") long id) {
		Optional<LtzCtlPeriodes> re = service.getOnePeriode(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlPeriodes> saveperiode(@RequestBody LtzCtlPeriodes entity) {
		return service.saveOnePeriode(entity);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlPeriodes> updateperiode(@RequestBody LtzCtlPeriodes entity) {
		return service.updateOnePeriode(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlPeriodes> updateperiodes(@RequestBody List<LtzCtlPeriodes> listEntity) {
		return service.updatePeriodes(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlPeriodes> deleteperiode(@RequestBody LtzCtlPeriodes entity) {
		return service.deleteOnPeriode(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlPeriodes> deleteperiodes(@RequestBody List<LtzCtlPeriodes> entity) {
		return service.deletePeriodes(entity);
	}
}
