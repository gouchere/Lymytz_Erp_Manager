package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlCustomerServeur;
import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlServices;
import lym.com.api.repository.base.AbonnementServiceRepo;
import lym.com.api.repository.base.CustomerRepo;
import lym.com.api.repository.base.CustomerServeurRepo;
import lym.com.api.repository.base.CustomerUsersRepo;
import lym.com.api.repository.jdbc.CustomJdbcRepo;
import lym.com.api.service.ICustomer;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Primary
public class ManagedCustomer implements ICustomer {

	@Autowired
	CustomerRepo service;
	
	@Autowired
	CustomJdbcRepo<LtzCtlAbonnements> repository;

	@Autowired
	CustomerUsersRepo serviceCompte;

	@Autowired
	CustomerServeurRepo serviceCS;
	
	@Autowired
	AbonnementServiceRepo serviceS;
	
	public Optional<LtzCtlCustomer> getOneCustomer(final Long id) {
		return service.findById(id);
	}

	public List<LtzCtlCustomer> findAllCustomer() {
		return service.findAll();
	}

	public List<LtzCtlCustomer> findAllCustomerActif(Boolean actif) {
		return service.findByActif(actif);
	}

	public List<LtzCtlServices> findByServiceByCustomerActif(LtzCtlCustomer customer) {
		return serviceS.findByCustomer(customer);
	}

	public ResultAction<LtzCtlCustomer> saveOneCustomer(LtzCtlCustomer entity) {
		ResultAction<LtzCtlCustomer> re = controleDataSave(entity);
		if (re.isResult()) {
			// On save d'abord l'adresse si disponible,
			try {
				entity.setId(null);
				entity = service.save(entity);
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

	@Override
	public ResultAction<LtzCtlCustomer> updateOneCustomer(LtzCtlCustomer entity) {
		ResultAction<LtzCtlCustomer> re = controleUpdateData(entity);
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

	@Override
	public ResultAction<LtzCtlCustomer> updateCustomer(List<LtzCtlCustomer> listEntity) {
		ResultAction<LtzCtlCustomer> re = new ResultAction<>(true);
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

	@Override
	public ResultAction<LtzCtlCustomer> deleteOneCustomer(LtzCtlCustomer entity) {
		ResultAction<LtzCtlCustomer> re = new ResultAction<>(true);
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

	@Override
	public ResultAction<LtzCtlCustomer> deleteManyCustomer(List<LtzCtlCustomer> listEntity) {
		ResultAction<LtzCtlCustomer> re = new ResultAction<>(true);
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

	public ResultAction<LtzCtlCustomerUser> addCompte(LtzCtlCustomerUser entity) {
		if (entity == null) {
			return new ResultAction<>(false, -1, "", "Aucune entité n'a été trouvé");
		} else if (entity.getIdCustomer() != null ? entity.getIdCustomer().getId() < 1 : true) {
			return new ResultAction<>(false, -1, "Attribut IdCustomer IS NULL", "Indiquez le client");
		} else if (entity.getIdUser() != null ? entity.getIdUser().getId() < 1 : true) {
			return new ResultAction<>(false, -1, "Attribut IdUser IS NULL", "Indiquez l'tutilisateur");
		}
		ResultAction<LtzCtlCustomerUser> re = new ResultAction<>(true);
		try {
			entity.setId(null);
			entity = serviceCompte.save(entity);
			re.setEntity(entity);
		} catch (Exception e) {
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setEntity(null);
			re.setResult(false);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlCustomer> controleUpdateData(LtzCtlCustomer entity) {
		ResultAction<LtzCtlCustomer> re = controleDataSave(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Id IS NULL", "L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

	public ResultAction<LtzCtlCustomer> controleDataSave(LtzCtlCustomer entity) {
		ResultAction<LtzCtlCustomer> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getRaisonSocial())) {
			re = new ResultAction<>(false, -1, "Attribut Raison social IS NULL", "Indiquez votre raison sociale");
		} else if (!ConstantesManager.isString(entity.getAdresse())) {
			re = new ResultAction<>(false, -1, "Attribut Adresse IS NULL", "Vous devez indique une adresse");
		} else if (!ConstantesManager.isString(entity.getTelephone())) {
			re = new ResultAction<>(false, -1, "Attribut Telephone IS NULL",
					"Vous devez indiquer le numéro de téléphone");
		} else if (!ConstantesManager.isString(entity.getPays())) {
			re = new ResultAction<>(false, -1, "Attribut Payx IS NULL", "Vous devez indiquer le pays");
		} else if (!ConstantesManager.isString(entity.getVille())) {
			re = new ResultAction<>(false, -1, "Attribut Ville IS NULL", "Vous devez indiquer la ville");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	/**
	 * Gérer la confirmation du customer et gestion de la table customer_serveur
	 **/
	public ResultAction<LtzCtlCustomerServeur> addCustomerServeur(LtzCtlCustomerServeur entity) {
		ResultAction<LtzCtlCustomerServeur> re = controleAddServeur(entity);
		if (re.isResult()) {
			entity.setId(null);
			serviceCS.save(entity);
			re.setCodeInfo(200);
			re.setCodeResult("");
			re.setMessage("Succes");
			re.setEntity(entity);
		}
		return re;
	}

	public ResultAction<LtzCtlCustomerServeur> updateCustomerServeur(LtzCtlCustomerServeur entity) {
		ResultAction<LtzCtlCustomerServeur> re = controleUpdateDataServeur(entity);
		if (re.isResult()) {
			serviceCS.save(entity);
			re.setCodeInfo(200);
			re.setCodeResult("");
			re.setMessage("Succes");
			re.setEntity(entity);
		}
		return re;
	}

	private ResultAction<LtzCtlCustomerServeur> controleUpdateDataServeur(LtzCtlCustomerServeur entity) {
		ResultAction<LtzCtlCustomerServeur> re = controleAddServeur(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Id IS NULL", "L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

	private ResultAction<LtzCtlCustomerServeur> controleAddServeur(LtzCtlCustomerServeur entity) {
		ResultAction<LtzCtlCustomerServeur> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "", "Aucune entité n'a été trouvé");
		} else if (entity.getCustomer() == null) {
			re = new ResultAction<>(false, -1, "Attribut customer IS NULL", "Veuillez indiquer le client");
		} else if (!ConstantesManager.isLong(entity.getCustomer().getId())) {
			re = new ResultAction<>(false, -1, "Attribut customer.id IS NULL", "Veuillez indiquer le client");
		} else if (entity.getServeur() == null) {
			re = new ResultAction<>(false, -1, "Attribut serveur IS NULL", "Vous devez indiquer le serveur");
		} else if (!ConstantesManager.isLong(entity.getServeur().getId())) {
			re = new ResultAction<>(false, -1, "Attribut serveur.id IS NULL", "Vous devez indiquer le serveur");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}
	
	public List<LtzCtlCustomer> findAllSqlCustomer(){
		String query = "SELECT * FROM ltz_ctl_abonnements y INNER JOIN ltz_ctl_customer c ON c.id=y.customer "
				+ "INNER JOIN ltz_ctl_licences l ON l.id=y.licence";
		List<LtzCtlAbonnements> re=repository.loadDataWithJdbc(LtzCtlAbonnements.class, query);
//		String query = "SELECT * FROM ltz_ctl_licences l LEFT JOIN ltz_ctl_remises r ON r.id=l.remise";
//		List<LtzCtlLicences> re=repository.loadDataWithJdbc(LtzCtlLicences.class, query);
//		return CustomerEntityBuilder.buildCustomers(re.getResult(), re.getColumns());
		return new ArrayList<LtzCtlCustomer>();
	}

}
