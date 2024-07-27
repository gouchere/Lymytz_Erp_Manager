package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlAssistances;
import lym.com.api.service.commons.ResultAction;

public interface IAssistances {
	public Optional<LtzCtlAssistances> getOneAssistance(Long id);

	public List<LtzCtlAssistances> findAllAssistances();

	public List<LtzCtlAssistances> findAllAssistanceActif(Boolean actif);

	public ResultAction<LtzCtlAssistances> saveOneAssistance(LtzCtlAssistances entity);

	public ResultAction<LtzCtlAssistances> updateOneAssistance(LtzCtlAssistances entity);

	public ResultAction<LtzCtlAssistances> updateAssistances(List<LtzCtlAssistances> listEntity);

	public ResultAction<LtzCtlAssistances> deleteOneAssistance(LtzCtlAssistances entity);

	public ResultAction<LtzCtlAssistances> deleteAssistances(List<LtzCtlAssistances> listEntity);

	public ResultAction<LtzCtlAssistances> controleData(LtzCtlAssistances entity);

	public ResultAction<LtzCtlAssistances> controleUpdateData(LtzCtlAssistances entity);
}
