package lym.com.api.repository.base;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.model.builder.CustomerUserEntityBuilder;

@Repository
public interface CustomerUsersRepo extends JpaRepository<LtzCtlCustomerUser, Long>{
	
	public Optional<LtzCtlCustomerUser> findByIdUser(LtzCtlUsers IdUser);

	public List<LtzCtlUsers> findByIdCustomer(LtzCtlCustomer IdCustomer);
	
	@Query(value = "SELECT y.* FROM yvs_ctl_customer y ", nativeQuery = true)
	public List<Object[]> findAllCustumer(LtzCtlCustomer IdCustomer);
	
	@Query(value = "SELECT y.idUser FROM LtzCtlCustomerUser y JOIN FETCH y.idUser.niveau WHERE y.idCustomer.id=:id")
	public List<LtzCtlUsers> findAllUserByCustumer(@Param("id") Long IdCustomer);
	
	@Query(value = "SELECT "+CustomerUserEntityBuilder.colonnes+" FROM ltz_ctl_customer_user y LEFT JOIN ltz_ctl_customer c ON y.id_customer = c.id LEFT JOIN ltz_ctl_customer_serveur cs ON cs.customer = c.id LEFT JOIN ltz_ctl_serveurs s ON cs.serveur = s.id WHERE y.id_user = :users LIMIT 1", nativeQuery = true)
	public List<Object[]> findByUsers(@Param("users") Long users);
	
}
