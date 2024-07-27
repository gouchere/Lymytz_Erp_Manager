package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlAssistances;

@Repository
public interface AssistanceRepo extends JpaRepository<LtzCtlAssistances, Long>{

	public List<LtzCtlAssistances> findByActif(Boolean actif);
	
}
