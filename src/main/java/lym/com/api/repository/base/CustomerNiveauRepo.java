package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlUsers;

@Repository
public interface CustomerNiveauRepo extends JpaRepository<LtzCtlCustomerUser, Long>{

	public List<LtzCtlUsers> findByIdCustomer(LtzCtlCustomer IdCustomer);
	
	@Query(value = "SELECT y.* FROM yvs_ctl_customer y ", nativeQuery = true)
	public List<Object[]> findAllCustumer(LtzCtlCustomer IdCustomer);
	
}
