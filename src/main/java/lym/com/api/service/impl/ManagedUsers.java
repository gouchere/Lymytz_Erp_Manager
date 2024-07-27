package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.model.builder.CustomerUserEntityBuilder;
import lym.com.api.repository.base.CustomerUsersRepo;
import lym.com.api.repository.base.UsersRepo;
import lym.com.api.repository.jdbc.JdbcUserRepo;
import lym.com.api.service.IUsers;
import lym.com.api.service.authentification.IHashPassWord;
import lym.com.api.service.authentification.MotDePasse;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedUsers implements IUsers {

	@Autowired
	private UsersRepo service;
	@Autowired
	private CustomerUsersRepo serviceCu;

	@Autowired
	private IHashPassWord encriptService;

	@Autowired
	JdbcUserRepo serviceJdbc;

	public ManagedUsers() {

	}

	@Override
	public Optional<LtzCtlUsers> getOneUser(Long id) {
		Optional<LtzCtlUsers> re = service.findById(id);
		if (re != null ? re.isPresent() : false) {
			re.get().setCompte(getCompte(re.get()));
		}
		return re;
	}

	public Optional<LtzCtlUsers> findOneByCode(String codeUser) {
		Optional<LtzCtlUsers> re=Optional.empty();
		try {
			 re = service.findByCodeUser(codeUser);
			if (re != null ? re.isPresent() : false) {
				re.get().setCompte(getCompte(re.get()));
			}
		} catch (Exception e) {
			System.err.println("Erreur lors de la récupération");
		}
		return re;
	}

	public LtzCtlCustomerUser getCompte(LtzCtlUsers entity) {
		if (entity != null ? ConstantesManager.isLong(entity.getId()) : false) {
			return CustomerUserEntityBuilder.buildOneFromObject(serviceCu.findByUsers(entity.getId()));
		} else {
			return new LtzCtlCustomerUser();
		}
	}

	@Override
	public List<LtzCtlUsers> findAllUsers() {
		return service.findAll();
	}

	@Override
	public List<LtzCtlUsers> findAllUserActif(Boolean actif) {
		return service.findByActif(actif);
	}

	@Override
	public List<LtzCtlUsers> findAllUserAdmin() {
		return serviceJdbc.findAllWhenNotCustomer();
	}

	@Override
	public List<LtzCtlUsers> findAllUserCustomer(Long idCustomer) {
		return serviceCu.findAllUserByCustumer(idCustomer);
	}

	@Override
	public ResultAction<LtzCtlUsers> saveOneUser(LtzCtlUsers entity) {
		ResultAction<LtzCtlUsers> re = controleData(entity);
		if (re.isResult()) {
			try {
				// hacher le password avant de le sauvegarder
				MotDePasse pass = encriptService.hashPassword(entity.getMotDePasse(),
						ConstantesManager.MD5S_ENCRIPT_METHODE);
				entity.setMotDePasse(pass.getPassword());
				entity.setSalt(pass.getSalt());
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
	public ResultAction<LtzCtlUsers> updateOneUser(LtzCtlUsers entity) {
		ResultAction<LtzCtlUsers> re = controleUpdateData(entity);
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
	public ResultAction<LtzCtlUsers> updateUsers(List<LtzCtlUsers> listEntity) {
		ResultAction<LtzCtlUsers> re;
		List<LtzCtlUsers> l = new ArrayList<>();
		for (LtzCtlUsers y : listEntity) {
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
	public ResultAction<LtzCtlUsers> deleteOnUser(LtzCtlUsers entity) {
		ResultAction<LtzCtlUsers> re;
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
	public ResultAction<LtzCtlUsers> deleteUsers(List<LtzCtlUsers> listEntity) {
		ResultAction<LtzCtlUsers> re;
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
	public ResultAction<LtzCtlUsers> controleData(LtzCtlUsers entity) {
		ResultAction<LtzCtlUsers> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getCodeUser())) {
			re = new ResultAction<>(false, -1, "Attribut libele IS NULL", "Indiquez un code pour ce User d'accès");
		} else if (!ConstantesManager.isString(entity.getNom())) {
			re = new ResultAction<>(false, -1, "Attribut nom IS NULL", "Indiquez le nom de l'utilisateur");
		} else if (!ConstantesManager.isString(entity.getEmail())) {
			re = new ResultAction<>(false, -1, "Attribut email IS NULL", "Indiquez l'adresse email de l'utilisateur");
		} else if (!ConstantesManager.isString(entity.getMotDePasse())) {
			re = new ResultAction<>(false, -1, "Attribut motdePasse IS NULL",
					"Indiquez le mot de passe de l'utilisateur");
		} else if (entity.getNiveau() == null) {
			re = new ResultAction<>(false, -1, "Attribut niveau IS NULL", "Vous devez renseigner le niveau d'accès");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlUsers> controleUpdateData(LtzCtlUsers entity) {
		ResultAction<LtzCtlUsers> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

}
