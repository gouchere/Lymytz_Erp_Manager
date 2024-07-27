package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlServices;
import lym.com.api.model.builder.ServiceEntityBuilder;

@Repository
public interface ServiceRepo extends JpaRepository<LtzCtlServices, Long>{

	public List<LtzCtlServices> findByActif(Boolean actif);
	
	/*retourne la liste des services en spécifiant les services qui sont lié au service principal*/
	@Query(value="SELECT l.id isS,"+ServiceEntityBuilder.colonnes+" FROM ltz_ctl_services y LEFT JOIN ltz_ctl_liaison_service l ON (l.service_lie=y.id AND l.service_principal=:id)"
			+ "WHERE y.actif IS TRUE AND y.id!=:id ORDER BY l.id ASC", nativeQuery = true)
	public List<Object[]> findAllSubService(@Param("id") Long id);
	
}
