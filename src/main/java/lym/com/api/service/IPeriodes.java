package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlPeriodes;
import lym.com.api.service.commons.ResultAction;

public interface IPeriodes {
	public Optional<LtzCtlPeriodes> getOnePeriode(Long id);

	public List<LtzCtlPeriodes> findAllPeriodes();

	public List<LtzCtlPeriodes> findAllPeriodeActif(Boolean actif);

	public ResultAction<LtzCtlPeriodes> saveOnePeriode(LtzCtlPeriodes entity);

	public ResultAction<LtzCtlPeriodes> updateOnePeriode(LtzCtlPeriodes entity);

	public ResultAction<LtzCtlPeriodes> updatePeriodes(List<LtzCtlPeriodes> listEntity);

	public ResultAction<LtzCtlPeriodes> deleteOnPeriode(LtzCtlPeriodes entity);

	public ResultAction<LtzCtlPeriodes> deletePeriodes(List<LtzCtlPeriodes> listEntity);

	public ResultAction<LtzCtlPeriodes> controleData(LtzCtlPeriodes entity);

	public ResultAction<LtzCtlPeriodes> controleUpdateData(LtzCtlPeriodes entity);
}
