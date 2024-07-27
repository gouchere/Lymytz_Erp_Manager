package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlAbonnementsAssistances;

@Repository
public interface AbonnementAssistanceRepo extends JpaRepository<LtzCtlAbonnementsAssistances, Long>{

	public List<LtzCtlAbonnementsAssistances> findByActif(Boolean actif);
	
	@Query("SELECT y FROM LtzCtlAbonnementsAssistances y JOIN FETCH y.assistance WHERE y.abonnement=:abonnement")
	public List<LtzCtlAbonnementsAssistances> findByAbonnement(@Param("abonnement") LtzCtlAbonnements abon);
	
}
