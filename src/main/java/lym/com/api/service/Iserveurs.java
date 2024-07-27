package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlServeurs;
import lym.com.api.service.commons.ResultAction;

public interface Iserveurs {
	public Optional<LtzCtlServeurs> getOneServeur(final Long id);

	public List<LtzCtlServeurs> findAllServeur();

	public List<LtzCtlServeurs> findAllServeurActif(Boolean actif);

	public ResultAction<LtzCtlServeurs> saveOneServeur(LtzCtlServeurs entity);

	public ResultAction<LtzCtlServeurs> updateOneServeur(LtzCtlServeurs entity);

	public ResultAction<LtzCtlServeurs> updateServeurs(List<LtzCtlServeurs> listEntity);

	public ResultAction<LtzCtlServeurs> controleDataSave(LtzCtlServeurs entity);

	public ResultAction<LtzCtlServeurs> controleUpdateData(LtzCtlServeurs entity);

	public ResultAction<LtzCtlServeurs> deleteOneServeur(LtzCtlServeurs entity);

	public ResultAction<LtzCtlServeurs> deleteManyServeurs(List<LtzCtlServeurs> listEntity);
}
