package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlLicences;
import lym.com.api.service.commons.ResultAction;

public interface ILicenceService {
	public Optional<LtzCtlLicences> getOneLicence(Long id);
	
	public Optional<LtzCtlLicences> findByCustomer(Long customer);

	public List<LtzCtlLicences> findAllLicences();

	public List<LtzCtlLicences> findAllLicenceActif(Boolean actif);

	public List<LtzCtlLicences> findAllLicenceDemo(Boolean demo);

	public ResultAction<LtzCtlLicences> saveOneLicence(LtzCtlLicences entity);

	public ResultAction<LtzCtlLicences> updateOneLicence(LtzCtlLicences entity);

	public ResultAction<LtzCtlLicences> updateLicences(List<LtzCtlLicences> listEntity);

	public ResultAction<LtzCtlLicences> deleteOneLicence(LtzCtlLicences entity);

	public ResultAction<LtzCtlLicences> deleteLicences(List<LtzCtlLicences> listEntity);

	public ResultAction<LtzCtlLicences> controleData(LtzCtlLicences entity);

	public ResultAction<LtzCtlLicences> controleUpdateData(LtzCtlLicences entity);
}
