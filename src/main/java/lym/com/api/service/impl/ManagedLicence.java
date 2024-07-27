package lym.com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlLicences;
import lym.com.api.repository.base.AbonnementRepo;
import lym.com.api.repository.base.LicenceRepo;
import lym.com.api.service.ILicenceService;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Primary
public class ManagedLicence implements ILicenceService{

	@Autowired
	LicenceRepo service;
	
	@Autowired
	AbonnementRepo serviceA;

	public Optional<LtzCtlLicences> getOneLicence(Long id) {
		return service.findById(id);
	}

	public Optional<LtzCtlLicences> findByCustomer(Long customer) {
		return service.findByCustomer(customer);
	}

	public List<LtzCtlLicences> findAllLicences() {
		return service.findAll();
	}

	public List<LtzCtlLicences> findAllLicenceActif(Boolean actif) {
		return service.findByActif(actif);
	}

	public List<LtzCtlLicences> findAllLicenceDemo(Boolean demo) {
		return service.findByDemo(demo);
	}

	public ResultAction<LtzCtlLicences> saveOneLicence(LtzCtlLicences entity) {
		ResultAction<LtzCtlLicences> re = controleData(entity);
		if (re.isResult()) {
			try {
				entity.setId(null);
				entity = service.save(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				// TODO: handle exception
				re.setCodeInfo(-1);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlLicences> updateOneLicence(LtzCtlLicences entity) {
		ResultAction<LtzCtlLicences> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				service.save(entity);
				re.setEntity(entity);
			} catch (Exception e) {
				// TODO: handle exception
				re.setCodeInfo(-1);
				re.setMessage(e.getMessage());
			}
		}
		return re;
	}

	public ResultAction<LtzCtlLicences> updateLicences(List<LtzCtlLicences> listEntity) {
		ResultAction<LtzCtlLicences> re = new ResultAction<>(true);
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
	public ResultAction<LtzCtlLicences> deleteOneLicence(LtzCtlLicences entity) {
		ResultAction<LtzCtlLicences> re = new ResultAction<>(true);
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

	public ResultAction<LtzCtlLicences> deleteLicences(List<LtzCtlLicences> listEntity) {
		ResultAction<LtzCtlLicences> re = new ResultAction<>(true);
//		Iterator<LtzCtlLicences> l = (Iterator<LtzCtlLicences>)listEntity;
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

	public ResultAction<LtzCtlLicences> controleData(LtzCtlLicences entity) {
		ResultAction<LtzCtlLicences> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getCodeLicence())) {
			re = new ResultAction<>(false, -1, "", "Un code de licence est requis");
		} else if (entity.getNbUser() != null ? (entity.getNbUser() < -1 || entity.getNbUser() == 0) : true) {
			re = new ResultAction<>(false, -1, "", "Vous devez préciser le nombre d'utilisateurs");
		} else if (!ConstantesManager.isDouble(entity.getMontant())) {
			re = new ResultAction<>(false, -1, "", "Vous devez préciser le montant de la licence");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	public ResultAction<LtzCtlLicences> controleUpdateData(LtzCtlLicences entity) {
		ResultAction<LtzCtlLicences> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "", "L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

}
