package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlRessources;
import lym.com.api.service.commons.ResultAction;

public interface IRessources {
	public Optional<LtzCtlRessources> getOneRessource(Long id);

	public List<LtzCtlRessources> findAllRessources();

	public ResultAction<LtzCtlRessources> saveOneRessource(LtzCtlRessources entity);

	public ResultAction<LtzCtlRessources> updateOneRessource(LtzCtlRessources entity);

	public ResultAction<LtzCtlRessources> updateRessources(List<LtzCtlRessources> listEntity);

	public ResultAction<LtzCtlRessources> deleteOneRessource(LtzCtlRessources entity);

	public ResultAction<LtzCtlRessources> deleteRessources(List<LtzCtlRessources> listEntity);

	public ResultAction<LtzCtlRessources> controleData(LtzCtlRessources entity);

	public ResultAction<LtzCtlRessources> controleUpdateData(LtzCtlRessources entity);
}
