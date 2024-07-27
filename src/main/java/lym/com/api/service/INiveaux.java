package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlAutorisations;
import lym.com.api.model.base.LtzCtlCustomerNiveaux;
import lym.com.api.model.base.LtzCtlNiveaux;
import lym.com.api.service.commons.ResultAction;

public interface INiveaux {
	public Optional<LtzCtlNiveaux> getOneNiveau(Long id);

	public List<LtzCtlNiveaux> findAllNiveaux();

	public List<LtzCtlNiveaux> findAllNiveauActif(Boolean actif);

	public List<LtzCtlNiveaux> findAllNiveauByPublic(Boolean publique);

	public Optional<LtzCtlNiveaux> findDefautNiveau();

	public ResultAction<LtzCtlNiveaux> saveOneNiveau(LtzCtlNiveaux entity);

	public ResultAction<LtzCtlNiveaux> updateOneNiveau(LtzCtlNiveaux entity);

	public ResultAction<LtzCtlNiveaux> updateNiveaux(List<LtzCtlNiveaux> listEntity);

	public ResultAction<LtzCtlNiveaux> deleteOnNiveau(LtzCtlNiveaux entity);

	public ResultAction<LtzCtlNiveaux> deleteNiveaux(List<LtzCtlNiveaux> listEntity);

	public ResultAction<LtzCtlNiveaux> controleData(LtzCtlNiveaux entity);

	public ResultAction<LtzCtlNiveaux> controleUpdateData(LtzCtlNiveaux entity);

	public ResultAction<LtzCtlCustomerNiveaux> saveOneCustomerNiveau(LtzCtlCustomerNiveaux entity);

	/** Gestion des autorisations **/
	public List<LtzCtlAutorisations> loadAutorisationsByNiveau(Long idNiveau);

	public ResultAction<LtzCtlAutorisations> addAutorisation(LtzCtlAutorisations entity);

	public ResultAction<LtzCtlAutorisations> addAutorisation(List<LtzCtlAutorisations> listEntity);

	public ResultAction<LtzCtlAutorisations> toogleAutorisation(LtzCtlAutorisations entity);
}
