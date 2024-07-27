package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlCustomerServeur;
import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlServices;
import lym.com.api.service.commons.ResultAction;

public interface ICustomer {
	public Optional<LtzCtlCustomer> getOneCustomer(final Long id);

	public List<LtzCtlCustomer> findAllSqlCustomer();

	public List<LtzCtlCustomer> findAllCustomer();

	public List<LtzCtlServices> findByServiceByCustomerActif(LtzCtlCustomer customer);

	public List<LtzCtlCustomer> findAllCustomerActif(Boolean actif);

	public ResultAction<LtzCtlCustomer> saveOneCustomer(LtzCtlCustomer entity);

	public ResultAction<LtzCtlCustomer> updateOneCustomer(LtzCtlCustomer entity);

	public ResultAction<LtzCtlCustomer> updateCustomer(List<LtzCtlCustomer> listEntity);

	public ResultAction<LtzCtlCustomer> controleDataSave(LtzCtlCustomer entity);

	public ResultAction<LtzCtlCustomer> controleUpdateData(LtzCtlCustomer entity);

	public ResultAction<LtzCtlCustomer> deleteOneCustomer(LtzCtlCustomer entity);

	public ResultAction<LtzCtlCustomer> deleteManyCustomer(List<LtzCtlCustomer> listEntity);

	public ResultAction<LtzCtlCustomerUser> addCompte(LtzCtlCustomerUser entity);

	/**
	 * Gérer la confirmation du customer et gestion de la table customer_serveur
	 **/
	public ResultAction<LtzCtlCustomerServeur> addCustomerServeur(LtzCtlCustomerServeur entity);

	public ResultAction<LtzCtlCustomerServeur> updateCustomerServeur(LtzCtlCustomerServeur entity);
}
