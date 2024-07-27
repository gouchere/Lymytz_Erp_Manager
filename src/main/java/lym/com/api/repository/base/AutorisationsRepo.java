package lym.com.api.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlAutorisations;
import lym.com.api.model.base.LtzCtlNiveaux;
import lym.com.api.model.base.LtzCtlRessources;
import lym.com.api.model.builder.AutorisationEntityBuilder;

@Repository
public interface AutorisationsRepo extends JpaRepository<LtzCtlAutorisations, Long> {

	@Query(value = "FROM LtzCtlAutorisations y WHERE y.niveau=:niveau AND y.ressource=:ressource")
	public Optional<LtzCtlAutorisations> findByNiveauAndRessource(@Param("niveau") LtzCtlNiveaux niveau,
			@Param("ressource") LtzCtlRessources ressource);
	
	@Query(value = "SELECT "+AutorisationEntityBuilder.colonnes+" FROM ltz_ctl_ressources y LEFT JOIN ltz_ctl_autorisations a ON (y.id=a.ressource AND a.niveau=:id) ", nativeQuery = true)
	public List<Object[]> findAllRessource(@Param("id") Long id);

}
