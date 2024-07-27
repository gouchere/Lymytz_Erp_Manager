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

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlLicences;
import lym.com.api.service.ILicenceService;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/licences")
public class LicenceController {

	public LicenceController() {
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	private LicenceService service;
	@Autowired
	ILicenceService service;

	@GetMapping("/list")
	public List<LtzCtlLicences> getLicences() {
		return service.findAllLicences();
	}

	@GetMapping("/licence/{id}")
	public LtzCtlLicences getLicences(@PathVariable("id") long id) {
		Optional<LtzCtlLicences> re = service.getOneLicence(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@GetMapping("/licence_by_customer/{customer}")
	public LtzCtlLicences findByCustomer(@PathVariable("customer") long customer) {
		Optional<LtzCtlLicences> re = service.findByCustomer(customer);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@GetMapping("/list_actif")
	public List<LtzCtlLicences> getLicences(@RequestHeader("actif")Boolean actif) {
		return service.findAllLicenceActif(actif);
	}

	@GetMapping("/list_demo")
	public List<LtzCtlLicences> getDemos(@RequestHeader("demo") Boolean demo) {
		return service.findAllLicenceDemo(demo);
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlLicences> saveLicence(@RequestBody LtzCtlLicences entity) {
		return service.saveOneLicence(entity);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlLicences> updateLicence(@RequestBody LtzCtlLicences entity) {
		return service.updateOneLicence(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlLicences> updateLicences(@RequestBody List<LtzCtlLicences> listEntity) {
		return service.updateLicences(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlLicences> deletLicence(@RequestBody LtzCtlLicences entity) {
		return service.deleteOneLicence(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlLicences> deletLicence(@RequestBody List<LtzCtlLicences> entity) {
		return service.deleteLicences(entity);
	}
}
