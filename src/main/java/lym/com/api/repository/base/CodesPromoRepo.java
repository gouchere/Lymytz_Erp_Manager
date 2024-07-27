package lym.com.api.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlCodePromo;

@Repository
public interface CodesPromoRepo extends JpaRepository<LtzCtlCodePromo, Long> {

	@Query("SELECT y FROM LtzCtlCodePromo y LEFT JOIN FETCH y.remise ORDER BY y.code")
	public List<LtzCtlCodePromo> findAll();
	
	@Query("SELECT y FROM LtzCtlCodePromo y LEFT JOIN FETCH y.remise WHERE y.actif=:actif ORDER BY y.code")
	public List<LtzCtlCodePromo> findByActif(@Param("actif")  Boolean actif);

	@Query("SELECT y FROM LtzCtlCodePromo y LEFT JOIN FETCH y.remise WHERE y.id=:id ORDER BY y.code")
	public Optional<LtzCtlCodePromo> findById(@Param("id") Long id);

}
