package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.service.commons.ResultAction;

public interface IUsers {
	public Optional<LtzCtlUsers> getOneUser(Long id);

	public Optional<LtzCtlUsers> findOneByCode(String codeUser);

	public List<LtzCtlUsers> findAllUsers();

	public List<LtzCtlUsers> findAllUserAdmin();

	public List<LtzCtlUsers> findAllUserCustomer(Long idCustomer);

	public List<LtzCtlUsers> findAllUserActif(Boolean actif);

	public ResultAction<LtzCtlUsers> saveOneUser(LtzCtlUsers entity);

	public ResultAction<LtzCtlUsers> updateOneUser(LtzCtlUsers entity);

	public ResultAction<LtzCtlUsers> updateUsers(List<LtzCtlUsers> listEntity);

	public ResultAction<LtzCtlUsers> deleteOnUser(LtzCtlUsers entity);

	public ResultAction<LtzCtlUsers> deleteUsers(List<LtzCtlUsers> listEntity);

	public ResultAction<LtzCtlUsers> controleData(LtzCtlUsers entity);

	public ResultAction<LtzCtlUsers> controleUpdateData(LtzCtlUsers entity);

	public LtzCtlCustomerUser getCompte(LtzCtlUsers entity);

}
