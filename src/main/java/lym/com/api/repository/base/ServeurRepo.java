package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlServeurs;

@Repository
public interface ServeurRepo extends JpaRepository<LtzCtlServeurs, Long>{

	public List<LtzCtlServeurs> findByActif(Boolean actif);
	
}
