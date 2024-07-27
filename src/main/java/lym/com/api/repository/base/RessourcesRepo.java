package lym.com.api.repository.base;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlRessources;

@Repository
public interface RessourcesRepo extends JpaRepository<LtzCtlRessources, Long>{
	
	
}
