package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlLiaisonService;
import lym.com.api.model.base.LtzCtlServices;
import lym.com.api.service.commons.ResultAction;

public interface IServices {
	public Optional<LtzCtlServices> getOneService(final Long id);

	public List<LtzCtlServices> findAll();

	public List<LtzCtlServices> findAllActif(Boolean actif);

	public List<LtzCtlServices> findServiceWithParent(Long id);

	public List<LtzCtlLiaisonService>  findByServicePrincipal(LtzCtlServices entity);

	public ResultAction<LtzCtlLiaisonService> addSubService(LtzCtlLiaisonService entity);

	public ResultAction<LtzCtlLiaisonService> removeLiasonService(LtzCtlLiaisonService entity);

	public ResultAction<LtzCtlServices> saveOneService(LtzCtlServices entity);

	public ResultAction<LtzCtlServices> saveServiceWithSubServices(LtzCtlServices entity);

	public ResultAction<LtzCtlServices> updateOneService(LtzCtlServices entity);

	public ResultAction<LtzCtlServices> updateServices(List<LtzCtlServices> listEntity);

	public ResultAction<LtzCtlServices> deleteOneService(LtzCtlServices entity);

	public ResultAction<LtzCtlServices> deleteServices(List<LtzCtlServices> listEntity);

	public ResultAction<LtzCtlServices> controleDataSave(LtzCtlServices entity);

	public ResultAction<LtzCtlServices> controleUpdateData(LtzCtlServices entity);
}
