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

import lym.com.api.model.base.LtzCtlCodePromo;  
import lym.com.api.service.ICodesPromo;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/codes_promo")
public class CodePromoController {

	public CodePromoController() {
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	private Ressourceservice service;
	@Autowired
	ICodesPromo service;

	@GetMapping("/list")
	public List<LtzCtlCodePromo> getCodesPromo() {
		return service.findAllCodesPromo();
	}

	@GetMapping("/CodePromo/{id}")
	public LtzCtlCodePromo getCodesPromo(@PathVariable("id") long id) {
		Optional<LtzCtlCodePromo> re = service.getOneCodePromo(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlCodePromo> saveCodePromo(@RequestBody LtzCtlCodePromo entity) {
		return service.saveOneCodePromo(entity);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlCodePromo> updateCodePromo(@RequestBody LtzCtlCodePromo entity) {
		return service.updateOneCodePromo(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlCodePromo> updateCodesPromo(@RequestBody List<LtzCtlCodePromo> listEntity) {
		return service.updateCodesPromo(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlCodePromo> deleteCodePromo(@RequestBody LtzCtlCodePromo entity) {
		return service.deleteOnCodePromo(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlCodePromo> deleteCodesPromo(@RequestBody List<LtzCtlCodePromo> entity) {
		return service.deleteCodesPromo(entity);
	}
}
