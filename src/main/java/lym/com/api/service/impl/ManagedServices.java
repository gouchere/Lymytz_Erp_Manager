package lym.com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlLiaisonService;
import lym.com.api.model.base.LtzCtlServices;
import lym.com.api.model.builder.ServiceEntityBuilder;
import lym.com.api.model.builder.ServiceLieEntityBuilder;
import lym.com.api.repository.base.ServiceLieRepo;
import lym.com.api.repository.base.ServiceRepo;
import lym.com.api.service.IServices;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Primary
public class ManagedServices implements IServices {

	@Autowired
	ServiceRepo service;
	@Autowired
	ServiceLieRepo daoSL;

	public Optional<LtzCtlServices> getOneService(final Long id) {
		Optional<LtzCtlServices> re = service.findById(id);
		if (re.isPresent()) {
			re.get().setServicesLies(ServiceLieEntityBuilder.buildListFromObject(service.findAllSubService(id),
					new LtzCtlServices(re.get().getId(), re.get().getCodeService(), re.get().getDesignation())));
			re.get();
		}
		return re;
	}

	public List<LtzCtlServices> findAll() {
		return service.findAll();
	}

	public List<LtzCtlServices> findAllActif(Boolean actif) {
		return service.findByActif(actif);
	}
	
	public List<LtzCtlLiaisonService> findByServicePrincipal(LtzCtlServices entity) {
		return daoSL.findByServicePrincipal(entity);
	}

	public List<LtzCtlServices> findServiceWithParent(Long id) {
//		return service.findByActif(actif);
		List<Object[]> list = service.findAllSubService(id);
		return ServiceEntityBuilder.buildListFromObject(list);
	}

	/*
	 * Ici on peut save un service avec ses services liés
	 */
	public ResultAction<LtzCtlServices> saveServiceWithSubServices(LtzCtlServices entity) {
		ResultAction<LtzCtlServices> re = saveOneService(entity);
		if (re.isResult()) {
			if (entity.getServicesLies() != null ? !entity.getServicesLies().isEmpty() : false) {
				for (LtzCtlLiaisonService e : entity.getServicesLies()) {
					if (e.getServiceLie() != null) {
						e.setId(null);
						e.setServicePrincipal(re.getEntity());
						e = daoSL.saveAndFlush(e);
					}
				}
			}else {
				entity.setServicesLies(ServiceLieEntityBuilder.buildListFromObject(service.findAllSubService(entity.getId()),
						new LtzCtlServices(entity.getId(), entity.getCodeService(), entity.getDesignation())));
				re.setEntity(entity);
			}
		}
		return re;
	}

	public ResultAction<LtzCtlLiaisonService> removeLiasonService(LtzCtlLiaisonService entity) {
		ResultAction<LtzCtlLiaisonService> re = new ResultAction<>(false);
		try {
			if (entity != null) {
				daoSL.delete(entity);
				re.setCodeInfo(200);
				entity.setId(null);
				re.setEntity(entity);
				re.setMessage("Succes");
				re.setResult(true);
			}
		} catch (Exception e) {
			re.setCodeInfo(-1);
			re.setEntity(null);
			re.setMessage(e.getMessage());
		}
		return re;
	}

	public ResultAction<LtzCtlLiaisonService> addSubService(LtzCtlLiaisonService entity) {
		ResultAction<LtzCtlLiaisonService> re = new ResultAction<>(false);
		try {
			if (entity != null) {
				entity.setId(null);
				if (entity.getServiceLie() != null ? ConstantesManager.isLong(entity.getServiceLie().getId()) : false) {
					if (entity.getServicePrincipal() != null
							? ConstantesManager.isLong(entity.getServicePrincipal().getId())
							: false) {
						entity = daoSL.save(entity);
						re.setCodeInfo(200);
						re.setEntity(entity);
						re.setMessage("Succes");
						re.setResult(true);
					} else {
						re.setCodeInfo(-1);
						re.setEntity(null);
						re.setMessage("Le service principal n'a pas été trouvé");
					}
				} else {
					re.setCodeInfo(-1);
					re.setEntity(null);
					re.setMessage("Le service de liaison n'a pas été trouvé");
				}
			} else {
				re.setCodeInfo(-1);
				re.setEntity(null);
				re.setMessage("Aucune entité n'a été trouvé");
				re.setCodeResult("ENTITY IS NULL");
			}
		} catch (Exception e) {
			re.setCodeInfo(-1);
			re.setEntity(null);
			re.setMessage(e.getMessage());
		}
		return re;
	}

	public ResultAction<LtzCtlServices> saveOneService(LtzCtlServices entity) {
		ResultAction<LtzCtlServices> re = controleDataSave(entity);
		if (re.isResult()) {
			// On save d'abord l'adresse si disponible,
			try {
				entity.setId(null);
				entity = service.saveAndFlush(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setEntity(null);
				re.setResult(false);
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServices> updateOneService(LtzCtlServices entity) {
		ResultAction<LtzCtlServices> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				entity = service.saveAndFlush(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				re.setCodeInfo(-1);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServices> updateServices(List<LtzCtlServices> listEntity) {
		ResultAction<LtzCtlServices> re = new ResultAction<>(true);
		if (listEntity != null) {
			try {
				service.saveAll(listEntity);
				re.setResult(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
			} catch (Exception e) {
				re.setCodeInfo(-1);
				re.setResult(false);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServices> deleteOneService(LtzCtlServices entity) {
		ResultAction<LtzCtlServices> re = new ResultAction<>(true);
		if (entity != null) {
			try {
				service.delete(entity);
				re.setEntity(entity);
				re.setMessage("Succes");
				re.setCodeInfo(200);
			} catch (Exception e) {
				re.setCodeInfo(-1);
				re.setResult(false);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServices> deleteServices(List<LtzCtlServices> listEntity) {
		ResultAction<LtzCtlServices> re = new ResultAction<>(true);
		if (listEntity != null) {
			try {
				service.deleteAll(listEntity);
				re.setResult(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
			} catch (Exception e) {
				re.setCodeInfo(-1);
				re.setResult(false);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlServices> controleDataSave(LtzCtlServices entity) {
		ResultAction<LtzCtlServices> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getDesignation())) {
			re = new ResultAction<>(false, -1, "Attribut designation IS NULL", "Indiquez une désignation");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	public ResultAction<LtzCtlServices> controleUpdateData(LtzCtlServices entity) {
		ResultAction<LtzCtlServices> re = controleDataSave(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "", "L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}
}
