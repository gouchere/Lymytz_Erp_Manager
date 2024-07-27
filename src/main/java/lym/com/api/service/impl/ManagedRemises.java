package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlRemises;
import lym.com.api.repository.base.RemisesRepo;
import lym.com.api.service.IRemises;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedRemises implements IRemises{
	
	@Autowired
	private RemisesRepo service;	

	@Override
	public Optional<LtzCtlRemises> getOneRemise(Long id) {
		Optional<LtzCtlRemises> re = service.findById(id);
		return re;
	}

	@Override
	public List<LtzCtlRemises> findAllRemises() {
		return service.findAll();
	}

	@Override
	public List<LtzCtlRemises> findAllRemiseActif(Boolean actif) {
		return service.findByActif(actif);
	}

	@Override
	public ResultAction<LtzCtlRemises> saveOneRemise(LtzCtlRemises entity) {
		ResultAction<LtzCtlRemises> re = controleData(entity);
		if (re.isResult()) {
			try {
				//hacher le password avant de le sauvegarder				
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
	public ResultAction<LtzCtlRemises> updateOneRemise(LtzCtlRemises entity) {
		ResultAction<LtzCtlRemises> re = controleUpdateData(entity);
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
	public ResultAction<LtzCtlRemises> updateRemises(List<LtzCtlRemises> listEntity) {
		ResultAction<LtzCtlRemises> re;
		List<LtzCtlRemises> l = new ArrayList<>();
		for (LtzCtlRemises y : listEntity) {
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
	public ResultAction<LtzCtlRemises> deleteOnRemise(LtzCtlRemises entity) {
		ResultAction<LtzCtlRemises> re;
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
	public ResultAction<LtzCtlRemises> deleteRemises(List<LtzCtlRemises> listEntity) {
		ResultAction<LtzCtlRemises> re;
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
	public ResultAction<LtzCtlRemises> controleData(LtzCtlRemises entity) {
		ResultAction<LtzCtlRemises> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isDouble(entity.getTaux())) {
			re = new ResultAction<>(false, -1, "Attribut taux IS NOT VALID", "Indiquez le taux de la remise");
		}else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlRemises> controleUpdateData(LtzCtlRemises entity) {
		ResultAction<LtzCtlRemises> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}


}
