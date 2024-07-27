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

import lym.com.api.model.base.LtzCtlRessources;
import lym.com.api.service.IRessources;
import lym.com.api.service.commons.ResultAction;

/** Classe Rest qui expose tous les traitements métier **/
@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/Ressources")
public class RessourcesController {

	public RessourcesController() {
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	private Ressourceservice service;
	@Autowired
	IRessources service;

	@GetMapping("/list")
	public List<LtzCtlRessources> getRessources() {
		return service.findAllRessources();
	}

	@GetMapping("/Ressource/{id}")
	public LtzCtlRessources getRessources(@PathVariable("id") long id) {
		Optional<LtzCtlRessources> re = service.getOneRessource(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	@Transactional
	public ResultAction<LtzCtlRessources> saveRessource(@RequestBody LtzCtlRessources entity) {
		return service.saveOneRessource(entity);
	}

	@PostMapping("/update")
	@Transactional
	public ResultAction<LtzCtlRessources> updateRessource(@RequestBody LtzCtlRessources entity) {
		return service.updateOneRessource(entity);
	}

	@PostMapping("/update_many")
	@Transactional
	public ResultAction<LtzCtlRessources> updateRessources(@RequestBody List<LtzCtlRessources> listEntity) {
		return service.updateRessources(listEntity);
	}

	@PostMapping("/delete")
	public ResultAction<LtzCtlRessources> deletRessource(@RequestBody LtzCtlRessources entity) {
		return service.deleteOneRessource(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlRessources> deletRessource(@RequestBody List<LtzCtlRessources> entity) {
		return service.deleteRessources(entity);
	}
}
