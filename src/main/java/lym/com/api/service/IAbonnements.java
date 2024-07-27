package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlAbonnementsAssistances;
import lym.com.api.model.base.LtzCtlAbonnementsRemises;
import lym.com.api.model.base.LtzCtlAbonnementsServices;
import lym.com.api.service.commons.ResultAction;

public interface IAbonnements {
	public Optional<LtzCtlAbonnements> getOneAbonnement(Long id);

	public List<LtzCtlAbonnements> findAllAbonnements();

	public List<LtzCtlAbonnements> findAllAbonnementActif(Boolean actif);

	public Optional<LtzCtlAbonnements> findByCustomer(Long customer);

	public ResultAction<LtzCtlAbonnements> saveOneAbonnement(LtzCtlAbonnements entity);

	public ResultAction<LtzCtlAbonnements> updateOneAbonnement(LtzCtlAbonnements entity);

	public ResultAction<LtzCtlAbonnements> updateAbonnements(List<LtzCtlAbonnements> listEntity);

	public ResultAction<LtzCtlAbonnements> deleteOnAbonnement(LtzCtlAbonnements entity);

	public ResultAction<LtzCtlAbonnements> deleteAbonnements(List<LtzCtlAbonnements> listEntity);

	public ResultAction<LtzCtlAbonnements> controleData(LtzCtlAbonnements entity);

	public ResultAction<LtzCtlAbonnements> controleUpdateData(LtzCtlAbonnements entity);

	public ResultAction<LtzCtlAbonnementsServices> addService(LtzCtlAbonnementsServices service);

	public ResultAction<LtzCtlAbonnementsServices> removeService(LtzCtlAbonnementsServices service);

	public ResultAction<LtzCtlAbonnementsAssistances> addAssistance(LtzCtlAbonnementsAssistances assistance);

	public ResultAction<LtzCtlAbonnementsAssistances> removeAssistance(LtzCtlAbonnementsAssistances assistance);

	public ResultAction<LtzCtlAbonnementsRemises> addRemise(LtzCtlAbonnementsRemises remise);

	public ResultAction<LtzCtlAbonnementsRemises> removeRemise(LtzCtlAbonnementsRemises remise);
}
