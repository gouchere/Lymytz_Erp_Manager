package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlAbonnementsRemises;

@Repository
public interface AbonnementRemiseRepo extends JpaRepository<LtzCtlAbonnementsRemises, Long>{

	public List<LtzCtlAbonnementsRemises> findByActif(Boolean actif);
	
	@Query("SELECT y FROM LtzCtlAbonnementsRemises y JOIN FETCH y.remise WHERE y.abonnement=:abonnement")
	public List<LtzCtlAbonnementsRemises> findByAbonnement(@Param("abonnement") LtzCtlAbonnements abon);
	
}
