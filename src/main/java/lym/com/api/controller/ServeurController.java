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

import lym.com.api.model.base.LtzCtlServeurs;
import lym.com.api.service.Iserveurs;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController()
@CrossOrigin("*")
@RequestMapping("erp_manager/serveurs")
public class ServeurController {

	@Autowired
	private Iserveurs service;

	@GetMapping("/serveur/{id}")
	public LtzCtlServeurs getServeurs(@PathVariable("id") long id) {
		Optional<LtzCtlServeurs> re = service.getOneServeur(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@GetMapping("/list")
	public List<LtzCtlServeurs> getServeurs() {
		return service.findAllServeur();
	}

	@GetMapping("/list_actif")
	public List<LtzCtlServeurs> getServeurs(Boolean actif) {
		return service.findAllServeurActif(actif);
	}

	@PostMapping("/save")
	public ResultAction<LtzCtlServeurs> saveServeur(@RequestBody LtzCtlServeurs entity) {
		return service.saveOneServeur(entity);
	}

	@PostMapping("/update")
	public ResultAction<LtzCtlServeurs> updateServeur(@RequestBody LtzCtlServeurs entity) {
		return service.updateOneServeur(entity);
	}

	@PostMapping("/update_many")
	public ResultAction<LtzCtlServeurs> updateServeurs(@RequestBody List<LtzCtlServeurs> listEntity) {
		return service.updateServeurs(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlServeurs> deletServeur(@RequestBody LtzCtlServeurs entity) {
		ResultAction<LtzCtlServeurs> result = new ResultAction<>(true);
		try {
			result = service.deleteOneServeur(entity);
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
	public ResultAction<LtzCtlServeurs> deletServeur(@RequestBody List<LtzCtlServeurs> entity) {
		return service.deleteManyServeurs(entity);
	}

}
