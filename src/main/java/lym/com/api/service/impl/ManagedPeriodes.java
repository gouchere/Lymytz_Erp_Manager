package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlPeriodes;
import lym.com.api.repository.base.PeriodesRepo;
import lym.com.api.service.IPeriodes;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedPeriodes implements IPeriodes{
	
	@Autowired
	private PeriodesRepo service;	

	@Override
	public Optional<LtzCtlPeriodes> getOnePeriode(Long id) {
		Optional<LtzCtlPeriodes> re = service.findById(id);
		return re;
	}

	@Override
	public List<LtzCtlPeriodes> findAllPeriodes() {
		return service.findAll();
	}

	@Override
	public List<LtzCtlPeriodes> findAllPeriodeActif(Boolean actif) {
		return service.findByActif(actif);
	}

	@Override
	public ResultAction<LtzCtlPeriodes> saveOnePeriode(LtzCtlPeriodes entity) {
		ResultAction<LtzCtlPeriodes> re = controleData(entity);
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
	public ResultAction<LtzCtlPeriodes> updateOnePeriode(LtzCtlPeriodes entity) {
		ResultAction<LtzCtlPeriodes> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				service.save(entity);
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
	public ResultAction<LtzCtlPeriodes> updatePeriodes(List<LtzCtlPeriodes> listEntity) {
		ResultAction<LtzCtlPeriodes> re;
		List<LtzCtlPeriodes> l = new ArrayList<>();
		for (LtzCtlPeriodes y : listEntity) {
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
	public ResultAction<LtzCtlPeriodes> deleteOnPeriode(LtzCtlPeriodes entity) {
		ResultAction<LtzCtlPeriodes> re;
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
	public ResultAction<LtzCtlPeriodes> deletePeriodes(List<LtzCtlPeriodes> listEntity) {
		ResultAction<LtzCtlPeriodes> re;
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
	public ResultAction<LtzCtlPeriodes> controleData(LtzCtlPeriodes entity) {
		ResultAction<LtzCtlPeriodes> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isInteger(entity.getNbMois())) {
			re = new ResultAction<>(false, -1, "Attribut nbMois IS NOT VALID", "Indiquez le nombre de mois de la période");
		}else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlPeriodes> controleUpdateData(LtzCtlPeriodes entity) {
		ResultAction<LtzCtlPeriodes> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}


}
