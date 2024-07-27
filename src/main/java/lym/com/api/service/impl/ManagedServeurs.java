package lym.com.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlServeurs;
import lym.com.api.repository.base.ServeurRepo;
import lym.com.api.service.Iserveurs;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Primary
public class ManagedServeurs implements Iserveurs {
	@Autowired
	ServeurRepo service;

	public Optional<LtzCtlServeurs> getOneServeur(final Long id) {
		return service.findById(id);
	}

	public List<LtzCtlServeurs> findAllServeur() {
		return StreamSupport.stream(service.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<LtzCtlServeurs> findAllServeurActif(Boolean actif) {
		return StreamSupport.stream(service.findByActif(actif).spliterator(), false).collect(Collectors.toList());
	}

	public ResultAction<LtzCtlServeurs> saveOneServeur(LtzCtlServeurs entity) {
		ResultAction<LtzCtlServeurs> re = controleDataSave(entity);
		if (re.isResult()) {
			try {
				entity.setId(null);
				entity = service.saveAndFlush(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				// TODO: handle exception
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setEntity(null);
				re.setResult(false);
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServeurs> updateOneServeur(LtzCtlServeurs entity) {
		ResultAction<LtzCtlServeurs> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				entity = service.saveAndFlush(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				// TODO: handle exception
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setEntity(null);
				re.setResult(false);
			}
		}
		return re;
	}
	
	public ResultAction<LtzCtlServeurs> updateServeurs(List<LtzCtlServeurs> listEntity) {
		ResultAction<LtzCtlServeurs> re = new ResultAction<>(true);
		if (listEntity != null) {
			try {
				service.saveAll(listEntity);
				re.setResult(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
			} catch (Exception e) {
				// TODO: handle exception
				re.setCodeInfo(-1);
				re.setResult(false);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServeurs> deleteOneServeur(LtzCtlServeurs entity) {
		ResultAction<LtzCtlServeurs> re = new ResultAction<>(true);
		if (entity != null) {
			try {
				service.delete(entity);
				re.setEntity(entity);
				re.setMessage("Succes");
				re.setCodeInfo(200);
			} catch (Exception e) {
				// TODO: handle exception
				re.setCodeInfo(-1);
				re.setResult(false);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServeurs> deleteManyServeurs(List<LtzCtlServeurs> listEntity) {
		ResultAction<LtzCtlServeurs> re = new ResultAction<>(true);
		if (listEntity != null) {
			try {
				service.deleteAll(listEntity);
				re.setResult(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
			} catch (Exception e) {
				// TODO: handle exception
				re.setCodeInfo(-1);
				re.setResult(false);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServeurs> controleDataSave(LtzCtlServeurs entity) {
		ResultAction<LtzCtlServeurs> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getAdresseIp())) {
			re = new ResultAction<>(false, -1, "Attribut AdresseIp IS NULL", "Indiquez une adresse Ip");
		} else if (!ConstantesManager.isString(entity.getPortWeb())) {
			re = new ResultAction<>(false, -1, "Attribut portWeb IS NULL", "Indiquez un numéro de port");
		} else if (!ConstantesManager.isString(entity.getNom())) {
			re = new ResultAction<>(false, -1, "Attribut nom IS NULL", "Indiquez un nom pour le serveur");
		} else if (entity.getPortWeb() == null) {
			re = new ResultAction<>(false, -1, "Attribut Licence IS NULL", "Vous devez choisir une Licene");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	public ResultAction<LtzCtlServeurs> controleUpdateData(LtzCtlServeurs entity) {
		ResultAction<LtzCtlServeurs> re = controleDataSave(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Id IS NULL", "L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}
}
