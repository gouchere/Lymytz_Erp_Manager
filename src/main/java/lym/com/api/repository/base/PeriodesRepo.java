package lym.com.api.repository.base;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlPeriodes;

@Repository
public interface PeriodesRepo extends JpaRepository<LtzCtlPeriodes, Long>{

	@Query("SELECT y FROM LtzCtlPeriodes y LEFT JOIN FETCH y.remise ORDER BY y.nbMois")
	public List<LtzCtlPeriodes> findAll();
	
	@Query("SELECT y FROM LtzCtlPeriodes y LEFT JOIN FETCH y.remise WHERE y.actif=:actif ORDER BY y.nbMois")
	public List<LtzCtlPeriodes> findByActif(@Param("actif") Boolean actif);
	
	@Query("SELECT y FROM LtzCtlPeriodes y LEFT JOIN FETCH y.remise WHERE y.id=:id ORDER BY y.nbMois")
	public Optional<LtzCtlPeriodes> findById(@Param("id") Long id);
	
}
