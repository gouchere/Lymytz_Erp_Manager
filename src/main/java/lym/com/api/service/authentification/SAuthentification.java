package lym.com.api.service.authentification;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.service.INiveaux;
import lym.com.api.service.IUsers;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Primary
public class SAuthentification implements IServiceAuthentification {

	@Autowired
	IUsers service;
	@Autowired
	INiveaux serviceN;
	@Autowired
	private IHashPassWord encriptService;

	public SAuthentification() {
	}

	@Override
	public ResultAction<LtzCtlUsers> authentification(String loggin, String password) {
		ResultAction<LtzCtlUsers> re = new ResultAction<>();
		re.setCodeInfo(-1);
		re.setEntity(null);
		re.setResult(false);
		Optional<LtzCtlUsers> user = service.findOneByCode(loggin);
		if (user.isPresent()) {
			MotDePasse pass = encriptService.decryptPassword(password, ConstantesManager.MD5S_ENCRIPT_METHODE,
					user.get().getSalt());
			if (pass != null) {
				if (pass.getPassword().equals(user.get().getMotDePasse())) {
					if (user.get().getNiveau() != null) {
						user.get().setCompte(service.getCompte(user.get()));
						user.get().getNiveau()
								.setAutorisations(serviceN.loadAutorisationsByNiveau(user.get().getNiveau().getId()));
						re.setCodeInfo(200);
						re.setEntity(user.get());
						re.setResult(true);
						re.setCodeResult("Succès");
						re.setMessage("Authentification réussi !");
					} else {
						re.setCodeResult(
								"Aucun niveau d'accès n'a été trouvé pour votre compte. Veuillez contacter votre administrateur");
						re.setMessage("Authentification non réussi !");
					}
				} else {
					re.setCodeResult("Mot de passe incorrect");
					re.setMessage("Authentification non réussi !");
				}
			} else {
				re.setCodeResult("Erreur lors du décryptage de votre mot de passe");
				re.setMessage("Erreur d'authentification");
			}
		} else {
			re.setCodeResult("Aucun utilisateur ne correspond à votre loggin");
			re.setMessage("Authentification non réussi !");
		}
		return re;
	}
}
