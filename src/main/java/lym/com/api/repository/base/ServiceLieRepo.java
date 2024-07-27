package lym.com.api.repository.base;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlLiaisonService;
import lym.com.api.model.base.LtzCtlServices;

@Repository
public interface ServiceLieRepo extends JpaRepository<LtzCtlLiaisonService, Long>{

	public List<LtzCtlLiaisonService> findByServicePrincipal(@Param("service")LtzCtlServices service);
	
}
