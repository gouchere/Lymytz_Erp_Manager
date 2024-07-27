package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlRemises;

@Repository
public interface RemisesRepo extends JpaRepository<LtzCtlRemises, Long>{

	public List<LtzCtlRemises> findByActif(Boolean actif);
	
}
