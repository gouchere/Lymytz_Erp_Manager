package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlCodePromo;
import lym.com.api.service.commons.ResultAction;

public interface ICodesPromo {
	public Optional<LtzCtlCodePromo> getOneCodePromo(Long id);

	public List<LtzCtlCodePromo> findAllCodesPromo();

	public List<LtzCtlCodePromo> findAllCodesPromoActif(Boolean actif);

	public ResultAction<LtzCtlCodePromo> saveOneCodePromo(LtzCtlCodePromo entity);

	public ResultAction<LtzCtlCodePromo> updateOneCodePromo(LtzCtlCodePromo entity);

	public ResultAction<LtzCtlCodePromo> updateCodesPromo(List<LtzCtlCodePromo> listEntity);

	public ResultAction<LtzCtlCodePromo> deleteOnCodePromo(LtzCtlCodePromo entity);

	public ResultAction<LtzCtlCodePromo> deleteCodesPromo(List<LtzCtlCodePromo> listEntity);

	public ResultAction<LtzCtlCodePromo> controleData(LtzCtlCodePromo entity);

	public ResultAction<LtzCtlCodePromo> controleUpdateData(LtzCtlCodePromo entity);

	public void loadDataSource();
}
