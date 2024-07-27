package lym.com.api.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlNiveaux;

@Repository
public interface NiveauxRepo extends JpaRepository<LtzCtlNiveaux, Long> {

	public List<LtzCtlNiveaux> findByActif(Boolean actif);

	public Optional<LtzCtlNiveaux> findByDefaut(Boolean defaut);
	
	@Query("SELECT y FROM LtzCtlNiveaux y WHERE y.publique=:publique")
	public List<LtzCtlNiveaux> findByPublic(@Param("publique") Boolean publique);

}
