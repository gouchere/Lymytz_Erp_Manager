package lym.com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlAbonnementsAssistances;
import lym.com.api.model.base.LtzCtlAbonnementsRemises;
import lym.com.api.model.base.LtzCtlAbonnementsServices;
import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.repository.base.AbonnementAssistanceRepo;
import lym.com.api.repository.base.AbonnementRemiseRepo;
import lym.com.api.repository.base.AbonnementRepo;
import lym.com.api.repository.base.AbonnementServiceRepo;
import lym.com.api.service.IAbonnements;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
public class ManagedAbonnement implements IAbonnements {

	@Autowired
	private AbonnementRepo service;
	@Autowired
	private AbonnementAssistanceRepo serviceA;
	@Autowired
	private AbonnementServiceRepo serviceS;
	@Autowired
	private AbonnementRemiseRepo serviceR;

	@Override
	public Optional<LtzCtlAbonnements> getOneAbonnement(Long id) {
		return service.findById(id);
	}

	@Override
	public List<LtzCtlAbonnements> findAllAbonnements() {
		return service.findAll();
	}

	@Override
	public List<LtzCtlAbonnements> findAllAbonnementActif(Boolean actif) {
		return service.findByActif(actif);
	}

	@Override
	public Optional<LtzCtlAbonnements> findByCustomer(Long customer) {
		Optional<LtzCtlAbonnements> re = service.findByCustomer(new LtzCtlCustomer(customer));
		if (re.isPresent()) {
			re.get().setAssistances(serviceA.findByAbonnement(re.get()));
			re.get().setServices(serviceS.findByAbonnement(re.get()));
			re.get().setRemises(serviceR.findByAbonnement(re.get()));
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAbonnements> saveOneAbonnement(LtzCtlAbonnements entity) {
		ResultAction<LtzCtlAbonnements> re = controleData(entity);
		if (re.isResult()) {
			try {
				entity.setId(null);
				entity = service.save(entity);
				re = new ResultAction<>(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
				re.setEntity(entity);
				if (entity.getServices() != null) {
					saveServices(entity.getServices(), entity);
					entity.getServices().stream().forEach((bean) -> {
						bean.setAbonnement(new LtzCtlAbonnements());
					});
				}
				if (entity.getAssistances() != null) {
					saveAssistances(entity.getAssistances(), entity);
					entity.getAssistances().stream().forEach((bean) -> {
						bean.setAbonnement(new LtzCtlAbonnements());
					});
				}
				if (entity.getRemises() != null) {
					saveRemisesAbonnements(entity.getRemises(), entity);
					entity.getRemises().stream().forEach((bean) -> {
						bean.setAbonnement(new LtzCtlAbonnements());
					});
				}
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
	public ResultAction<LtzCtlAbonnements> updateOneAbonnement(LtzCtlAbonnements entity) {
		ResultAction<LtzCtlAbonnements> re = controleUpdateData(entity);
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
	public ResultAction<LtzCtlAbonnements> updateAbonnements(List<LtzCtlAbonnements> listEntity) {
		return null;
	}

	@Override
	public ResultAction<LtzCtlAbonnements> deleteOnAbonnement(LtzCtlAbonnements entity) {
		return null;
	}

	@Override
	public ResultAction<LtzCtlAbonnements> deleteAbonnements(List<LtzCtlAbonnements> listEntity) {
		return null;
	}

	@Override
	public ResultAction<LtzCtlAbonnements> controleData(LtzCtlAbonnements entity) {
		ResultAction<LtzCtlAbonnements> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (entity.getCustomer() == null) {
			re = new ResultAction<>(false, -1, "Attribut Customer IS NULL", "Le client est requis");
		} else if (entity.getCustomer().getId() == null) {
			re = new ResultAction<>(false, -1, "Attribut customer.id IS NULL", "Le client est requis");
		} else if (entity.getLicence() == null) {
			re = new ResultAction<>(false, -1, "Attribut licence IS NULL", "La licence est requise");
		} else if (entity.getLicence().getId() == null) {
			re = new ResultAction<>(false, -1, "Attribut licence.id IS NULL", "La licence est requise");
		} else if (entity.getPeriodeFacturation() == null) {
			re = new ResultAction<>(false, -1, "Attribut periodeFacturation IS NULL",
					"Vous devez indiquer la période de facturation");
		} else if (entity.getPeriodeFacturation().getId() == null) {
			re = new ResultAction<>(false, -1, "Attribut periodeFacturation.id IS NULL",
					"Vous devez indiquer la période de facturation");
		} else if (!ConstantesManager.isInteger(entity.getPeriodeFacturation().getNbMois())) {
			re = new ResultAction<>(false, -1, "Attribut periodeFacturation.nbMois IS NOT VALID",
					"Vous devez indiquer la période de facturation");
		} else {
			re = new ResultAction<>(true, 200, "", "Succes");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAbonnements> controleUpdateData(LtzCtlAbonnements entity) {
		ResultAction<LtzCtlAbonnements> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

	public Boolean saveServices(List<LtzCtlAbonnementsServices> services, LtzCtlAbonnements abonnement) {
		services.stream().forEach((s) -> {
			s.setAbonnement(abonnement);
			if (controleSave(s).isResult()) {
				serviceS.save(s);
			}
		});
		return true;
	}

	public ResultAction<LtzCtlAbonnementsServices> controleSave(LtzCtlAbonnementsServices entity) {
		if (entity == null) {
			return new ResultAction<>(false, -1, "Entity LtzCtlAbonnementsServices  IS NULL",
					"Aucune entité n'a été trouvé");
		} else if (entity.getService() == null) {
			return new ResultAction<>(false, -1, "Attribut service IS NULL", "Vous devez indiquer le service");
		} else if (entity.getService().getId() == null) {
			return new ResultAction<>(false, -1, "Attribut service.id IS NULL", "Vous devez indiquer le service");
		} else if (entity.getAbonnement() == null) {
			return new ResultAction<>(false, -1, "Attribut abonnement IS NULL", "Vous devez indiquer l'abonnement");
		} else if (entity.getAbonnement().getId() == null) {
			return new ResultAction<>(false, -1, "Attribut abonnement.id IS NULL", "Vous devez indiquer l'abonnement");
		}
		return new ResultAction<>(true);
	}

	public Boolean saveAssistances(List<LtzCtlAbonnementsAssistances> services, LtzCtlAbonnements abonnement) {
		services.stream().forEach((s) -> {
			s.setAbonnement(abonnement);
			if (controleSave(s).isResult()) {
				serviceA.save(s);
			}
		});
		return true;
	}

	public ResultAction<LtzCtlAbonnementsAssistances> controleSave(LtzCtlAbonnementsAssistances entity) {
		if (entity == null) {
			return new ResultAction<>(false, -1, "Entity LtzCtlAbonnementsAssistances  IS NULL",
					"Aucune entité n'a été trouvé");
		} else if (entity.getAssistance() == null) {
			return new ResultAction<>(false, -1, "Attribut assistance IS NULL", "Vous devez indiquer le service");
		} else if (entity.getAssistance().getId() == null) {
			return new ResultAction<>(false, -1, "Attribut assistance.id IS NULL", "Vous devez indiquer le service");
		} else if (entity.getAbonnement() == null) {
			return new ResultAction<>(false, -1, "Attribut abonnement IS NULL", "Vous devez indiquer l'abonnement");
		} else if (entity.getAbonnement().getId() == null) {
			return new ResultAction<>(false, -1, "Attribut abonnement.id IS NULL", "Vous devez indiquer l'abonnement");
		}
		return new ResultAction<>(true);
	}

	public ResultAction<LtzCtlAbonnementsRemises> controleSave(LtzCtlAbonnementsRemises entity) {
		if (entity == null) {
			return new ResultAction<>(false, -1, "Entity LtzCtlAbonnementsRemises  IS NULL",
					"Aucune entité n'a été trouvé");
		} else if (entity.getRemise() == null) {
			return new ResultAction<>(false, -1, "Attribut remise IS NULL", "Vous devez indiquer la remise");
		} else if (entity.getRemise().getId() == null) {
			return new ResultAction<>(false, -1, "Attribut remise.id IS NULL", "Vous devez indiquer la remise");
		} else if (entity.getAbonnement() == null) {
			return new ResultAction<>(false, -1, "Attribut abonnement IS NULL", "Vous devez indiquer l'abonnement");
		} else if (entity.getAbonnement().getId() == null) {
			return new ResultAction<>(false, -1, "Attribut abonnement.id IS NULL", "Vous devez indiquer l'abonnement");
		}
		return new ResultAction<>(true);
	}

	public Boolean saveRemisesAbonnements(List<LtzCtlAbonnementsRemises> services, LtzCtlAbonnements abonnement) {
		services.stream().forEach((s) -> {
			s.setAbonnement(abonnement);
			if (controleSave(s).isResult()) {
				serviceR.save(s);
			}
		});
		return true;
	}

	@Override
	public ResultAction<LtzCtlAbonnementsServices> addService(LtzCtlAbonnementsServices service) {
		ResultAction<LtzCtlAbonnementsServices> re = new ResultAction<LtzCtlAbonnementsServices>();
		try {
			service = serviceS.save(service);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setEntity(service);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAbonnementsServices> removeService(LtzCtlAbonnementsServices service) {
		ResultAction<LtzCtlAbonnementsServices> re = new ResultAction<LtzCtlAbonnementsServices>();
		try {
			serviceS.delete(service);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setEntity(service);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAbonnementsAssistances> addAssistance(LtzCtlAbonnementsAssistances assistance) {
		ResultAction<LtzCtlAbonnementsAssistances> re = new ResultAction<LtzCtlAbonnementsAssistances>();
		try {
			assistance = serviceA.save(assistance);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setEntity(assistance);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAbonnementsAssistances> removeAssistance(LtzCtlAbonnementsAssistances assistance) {
		ResultAction<LtzCtlAbonnementsAssistances> re = new ResultAction<LtzCtlAbonnementsAssistances>();
		try {
			serviceA.delete(assistance);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setEntity(assistance);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAbonnementsRemises> addRemise(LtzCtlAbonnementsRemises remise) {
		ResultAction<LtzCtlAbonnementsRemises> re = new ResultAction<LtzCtlAbonnementsRemises>();
		try {
			remise = serviceR.save(remise);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setEntity(remise);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlAbonnementsRemises> removeRemise(LtzCtlAbonnementsRemises remise) {
		ResultAction<LtzCtlAbonnementsRemises> re = new ResultAction<LtzCtlAbonnementsRemises>();
		try {
			serviceR.delete(remise);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setEntity(remise);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}
}
