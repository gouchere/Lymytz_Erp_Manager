package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlRemises;
import lym.com.api.service.commons.ResultAction;

public interface IRemises {
	public Optional<LtzCtlRemises> getOneRemise(Long id);

	public List<LtzCtlRemises> findAllRemises();

	public List<LtzCtlRemises> findAllRemiseActif(Boolean actif);

	public ResultAction<LtzCtlRemises> saveOneRemise(LtzCtlRemises entity);

	public ResultAction<LtzCtlRemises> updateOneRemise(LtzCtlRemises entity);

	public ResultAction<LtzCtlRemises> updateRemises(List<LtzCtlRemises> listEntity);

	public ResultAction<LtzCtlRemises> deleteOnRemise(LtzCtlRemises entity);

	public ResultAction<LtzCtlRemises> deleteRemises(List<LtzCtlRemises> listEntity);

	public ResultAction<LtzCtlRemises> controleData(LtzCtlRemises entity);

	public ResultAction<LtzCtlRemises> controleUpdateData(LtzCtlRemises entity);
}
