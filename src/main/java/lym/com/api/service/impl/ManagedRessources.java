package lym.com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlRessources;
import lym.com.api.repository.base.RessourcesRepo;
import lym.com.api.service.IRessources;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Primary
public class ManagedRessources implements IRessources{

	@Autowired
	RessourcesRepo service;

	public Optional<LtzCtlRessources> getOneRessource(Long id) {
		return service.findById(id);
	}

	public List<LtzCtlRessources> findAllRessources() {
		return service.findAll();
	}


	public ResultAction<LtzCtlRessources> saveOneRessource(LtzCtlRessources entity) {
		ResultAction<LtzCtlRessources> re = controleData(entity);
		if (re.isResult()) {
			try {
				entity.setId(null);
				entity = service.saveAndFlush(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				// TODO: handle exception
				re.setCodeInfo(-1);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlRessources> updateOneRessource(LtzCtlRessources entity) {
		ResultAction<LtzCtlRessources> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				entity = service.saveAndFlush(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				// TODO: handle exception
				re.setCodeInfo(-1);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlRessources> updateRessources(List<LtzCtlRessources> listEntity) {
		ResultAction<LtzCtlRessources> re = new ResultAction<>(true);
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
	public ResultAction<LtzCtlRessources> deleteOneRessource(LtzCtlRessources entity) {
		ResultAction<LtzCtlRessources> re = new ResultAction<>(true);
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

	public ResultAction<LtzCtlRessources> deleteRessources(List<LtzCtlRessources> listEntity) {
		ResultAction<LtzCtlRessources> re = new ResultAction<>(true);
//		Iterator<LtzCtlRessources> l = (Iterator<LtzCtlRessources>)listEntity;
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

	public ResultAction<LtzCtlRessources> controleData(LtzCtlRessources entity) {
		ResultAction<LtzCtlRessources> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getRessourceName())) {
			re = new ResultAction<>(false, -1, "Attribut ressourceName IS NULL", "Un code de Ressource est requis");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	public ResultAction<LtzCtlRessources> controleUpdateData(LtzCtlRessources entity) {
		ResultAction<LtzCtlRessources> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "", "L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

}
