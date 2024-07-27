package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlAbonnementsServices;
import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlServices;

@Repository
public interface AbonnementServiceRepo extends JpaRepository<LtzCtlAbonnementsServices, Long>{

	public List<LtzCtlAbonnementsServices> findByActif(Boolean actif);
	
	@Query("SELECT y.service FROM LtzCtlAbonnementsServices y WHERE y.abonnement.customer=:customer")
	public List<LtzCtlServices> findByCustomer(@Param("customer")  LtzCtlCustomer customer);
	
	@Query("SELECT y FROM LtzCtlAbonnementsServices y JOIN FETCH y.service WHERE y.abonnement=:abonnement")
	public List<LtzCtlAbonnementsServices> findByAbonnement(@Param("abonnement") LtzCtlAbonnements abon);
	
}
