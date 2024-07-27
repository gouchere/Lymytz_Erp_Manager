package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlAssistances;
import lym.com.api.repository.base.AssistanceRepo;
import lym.com.api.service.IAssistances;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedAssistance implements IAssistances{
	
	@Autowired
	private AssistanceRepo service;	

	@Override
	public Optional<LtzCtlAssistances> getOneAssistance(Long id) {
		Optional<LtzCtlAssistances> re = service.findById(id);
		return re;
	}

	@Override
	public List<LtzCtlAssistances> findAllAssistances() {
		return service.findAll();
	}

	@Override
	public List<LtzCtlAssistances> findAllAssistanceActif(Boolean actif) {
		return service.findByActif(actif);
	}

	@Override
	public ResultAction<LtzCtlAssistances> saveOneAssistance(LtzCtlAssistances entity) {
		ResultAction<LtzCtlAssistances> re = controleData(entity);
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
	public ResultAction<LtzCtlAssistances> updateOneAssistance(LtzCtlAssistances entity) {
		ResultAction<LtzCtlAssistances> re = controleUpdateData(entity);
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
	public ResultAction<LtzCtlAssistances> updateAssistances(List<LtzCtlAssistances> listEntity) {
		ResultAction<LtzCtlAssistances> re;
		List<LtzCtlAssistances> l = new ArrayList<>();
		for (LtzCtlAssistances y : listEntity) {
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
	public ResultAction<LtzCtlAssistances> deleteOneAssistance(LtzCtlAssistances entity) {
		ResultAction<LtzCtlAssistances> re;
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
	public ResultAction<LtzCtlAssistances> deleteAssistances(List<LtzCtlAssistances> listEntity) {
		ResultAction<LtzCtlAssistances> re;
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
	public ResultAction<LtzCtlAssistances> controleData(LtzCtlAssistances entity) {
		ResultAction<LtzCtlAssistances> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		}else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAssistances> controleUpdateData(LtzCtlAssistances entity) {
		ResultAction<LtzCtlAssistances> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}


}
