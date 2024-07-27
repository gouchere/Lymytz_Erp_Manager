package lym.com.api.repository.base;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlCustomerServeur;

@Repository
public interface CustomerServeurRepo extends JpaRepository<LtzCtlCustomerServeur, Long>{
	
	@Query("SELECT y FROM LtzCtlCustomerServeur y LEFT JOIN FETCH y.customer JOIN FETCH y.serveur")
	public List<LtzCtlCustomerServeur> findAll();
	
}
