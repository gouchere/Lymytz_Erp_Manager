package lym.com.api.repository.base;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lym.com.api.model.base.LtzCtlAbonnements;
import lym.com.api.model.base.LtzCtlCustomer;

public interface AbonnementRepo extends JpaRepository<LtzCtlAbonnements, Long> {
	
	public List<LtzCtlAbonnements> findByActif(Boolean actif);

	@Query("SELECT y FROM LtzCtlAbonnements y LEFT JOIN FETCH y.customer C "
			+ "LEFT JOIN FETCH C.serveur S "
			+ "LEFT JOIN FETCH S.serveur "
			+ "LEFT JOIN FETCH y.licence L "
			+ "LEFT JOIN FETCH L.remise " 
			+ "LEFT JOIN FETCH y.periodeFacturation P "
			+ "LEFT JOIN FETCH P.remise "
			+ "WHERE y.customer = :customer")
//	@Query("SELECT y FROM LtzCtlAbonnements y LEFT JOIN FETCH y.customer LEFT JOIN FETCH y.licence LEFT JOIN FETCH y.licence.remise LEFT JOIN FETCH y.periodeFacturation LEFT JOIN FETCH y.periodeFacturation.remise WHERE y.customer = :customer")
	public Optional<LtzCtlAbonnements> findByCustomer(@Param("customer") LtzCtlCustomer customer);


}
