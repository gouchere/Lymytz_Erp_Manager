package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlAutorisations;
import lym.com.api.model.base.LtzCtlCustomerNiveaux;
import lym.com.api.model.base.LtzCtlNiveaux;
import lym.com.api.model.builder.AutorisationEntityBuilder;
import lym.com.api.repository.base.AutorisationsRepo;
import lym.com.api.repository.base.NiveauxRepo;
import lym.com.api.service.INiveaux;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedNiveaux implements INiveaux {

	@Autowired
	private NiveauxRepo service;
	@Autowired
	private AutorisationsRepo serviceAut;

	@Override
	public Optional<LtzCtlNiveaux> getOneNiveau(Long id) {
		Optional<LtzCtlNiveaux> re = service.findById(id);
		if (re.isPresent()) {
			re.get().setAutorisations(
					AutorisationEntityBuilder.buildListFromObject(serviceAut.findAllRessource(id), id));
		}
		return re;
	}

	@Override
	public Optional<LtzCtlNiveaux> findDefautNiveau() {
		Optional<LtzCtlNiveaux> re = service.findByDefaut(true);
		if (re.isPresent()) {
			re.get().setAutorisations(
					AutorisationEntityBuilder.buildListFromObject(serviceAut.findAllRessource(re.get().getId()), re.get().getId()));
		}
		return re;
	}

	@Override
	public List<LtzCtlNiveaux> findAllNiveaux() {
		return service.findAll();
	}

	@Override
	public List<LtzCtlNiveaux> findAllNiveauActif(Boolean actif) {
		return service.findByActif(actif);
	}

	@Override
	public List<LtzCtlNiveaux> findAllNiveauByPublic(Boolean publique){
		return service.findByPublic(publique);
	}
	
	@Override
	public ResultAction<LtzCtlNiveaux> saveOneNiveau(LtzCtlNiveaux entity) {
		ResultAction<LtzCtlNiveaux> re = controleData(entity);
		if (re.isResult()) {
			try {
				entity = service.save(entity);
				re = new ResultAction<>(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
				re.setEntity(entity);
			} catch (Exception e) {
				re = new ResultAction<>(false);
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setCodeResult("Internal Server Error");
			}
		}
		return re;
	}
	
	@Override
	public ResultAction<LtzCtlCustomerNiveaux> saveOneCustomerNiveau(LtzCtlCustomerNiveaux entity) {
		ResultAction<LtzCtlCustomerNiveaux> re=new ResultAction<LtzCtlCustomerNiveaux>() ;
			try {
				ResultAction<LtzCtlNiveaux> r = saveOneNiveau(entity.getNiveau());
				if(r.isResult()) {
					if(entity.getCustomer()!=null?ConstantesManager.isLong(entity.getCustomer().getId()):false) {
						re = new ResultAction<>(true);
						re.setMessage("Succes");
						re.setCodeInfo(200);
						re.setEntity(entity);
					}
				}				
			} catch (Exception e) {
				re = new ResultAction<>(false);
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setCodeResult("Internal Server Error");
			}		
		return re;
	}

	@Override
	public ResultAction<LtzCtlNiveaux> updateOneNiveau(LtzCtlNiveaux entity) {
		ResultAction<LtzCtlNiveaux> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				entity = service.save(entity);
				re = new ResultAction<>(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
				re.setEntity(entity);
			} catch (Exception e) {
				re = new ResultAction<>(false);
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setCodeResult("Internal Server Error");
			}
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlNiveaux> updateNiveaux(List<LtzCtlNiveaux> listEntity) {
		ResultAction<LtzCtlNiveaux> re;
		List<LtzCtlNiveaux> l = new ArrayList<>();
		for (LtzCtlNiveaux y : listEntity) {
			re = controleUpdateData(y);
			if (!re.isResult()) {
				l.add(y);
			}
		}
		listEntity.removeAll(l);
		try {
			service.saveAll(listEntity);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setListEntity(l);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlNiveaux> deleteOnNiveau(LtzCtlNiveaux entity) {
		ResultAction<LtzCtlNiveaux> re;
		try {
			service.delete(entity);
			re = new ResultAction<>(true);
			re.setCodeInfo(200);
			re.setCodeResult("");
			re.setMessage("Succes");
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
			re.setMessage(e.getMessage());
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlNiveaux> deleteNiveaux(List<LtzCtlNiveaux> listEntity) {
		ResultAction<LtzCtlNiveaux> re;
		try {
			service.deleteAll(listEntity);
			re = new ResultAction<>(true);
			re.setCodeInfo(200);
			re.setCodeResult("");
			re.setMessage("Succes");
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
			re.setMessage(e.getMessage());
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlNiveaux> controleData(LtzCtlNiveaux entity) {
		ResultAction<LtzCtlNiveaux> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getLibele())) {
			re = new ResultAction<>(false, -1, "Attribut libele IS NULL", "Indiquez un libellé pour ce niveau d'accès");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlNiveaux> controleUpdateData(LtzCtlNiveaux entity) {
		ResultAction<LtzCtlNiveaux> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

	/** Gestion des autorisations **/

	public List<LtzCtlAutorisations> loadAutorisationsByNiveau(Long idNiveau) {
		if (ConstantesManager.isLong(idNiveau)) {
			return AutorisationEntityBuilder.buildListFromObject(serviceAut.findAllRessource(idNiveau), idNiveau);
		} else {
			return new ArrayList<>();
		}
	}

	public ResultAction<LtzCtlAutorisations> addAutorisation(List<LtzCtlAutorisations> listEntity) {
		ResultAction<LtzCtlAutorisations> re;
		List<LtzCtlAutorisations> temps = new ArrayList<>();
		for (LtzCtlAutorisations y : listEntity) {
			if (!controleAddAutorisation(y).isResult()) {
				temps.add(y);
			} else {
				y.setId((y.getId() <= 0) ? null : y.getId());
			}
		}
		listEntity.removeAll(temps);
		try {
			serviceAut.saveAll(listEntity);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setListEntity(temps);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setCodeInfo(-1);
			re.setMessage(e.getMessage());
			re.setResult(false);
			re.setEntity(null);
		}

		return re;
	}

	public ResultAction<LtzCtlAutorisations> addAutorisation(LtzCtlAutorisations entity) {
		ResultAction<LtzCtlAutorisations> re = controleAddAutorisation(entity);
		if (re.isResult()) {
			try {
				serviceAut.save(entity);
				re.setEntity(entity);
				re.setCodeInfo(200);
				re.setMessage("Succes");
			} catch (Exception e) {
				re.setCodeInfo(-1);
				re.setMessage(e.getMessage());
				re.setResult(false);
				re.setEntity(null);
			}
		}

		return re;
	}

	public ResultAction<LtzCtlAutorisations> toogleAutorisation(LtzCtlAutorisations entity) {
		ResultAction<LtzCtlAutorisations> re = controleAddAutorisation(entity);
		if (re.isResult()) {
			try {

				serviceAut.save(entity);
			} catch (Exception e) {
				re.setCodeInfo(-1);
				re.setMessage(e.getMessage());
				re.setResult(false);
				re.setEntity(null);
			}
		}

		return re;
	}

	private ResultAction<LtzCtlAutorisations> controleAddAutorisation(LtzCtlAutorisations entity) {
		ResultAction<LtzCtlAutorisations> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "L'entité n'a pas été trouvé");
		} else if (entity.getNiveau() == null) {
			re = new ResultAction<>(false, -1, "Attribut niveau IS NULL", "Le niveau d'accès doit être renseigné");
		} else if (!ConstantesManager.isLong(entity.getNiveau().getId())) {
			re = new ResultAction<>(false, -1, "Attribut niveau.id IS NULL", "Le niveau d'accès doit être renseigné");
		} else if (entity.getRessource() == null) {
			re = new ResultAction<>(false, -1, "Attribut ressource IS NULL", "La ressource doit être renseigné");
		} else if (!ConstantesManager.isLong(entity.getRessource().getId())) {
			re = new ResultAction<>(false, -1, "Attribut ressource.id IS NULL", "La ressource doit être renseigné");
		} else
			re = new ResultAction<>(true);
		return re;
	}

}
