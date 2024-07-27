package lym.com.api.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlCustomer;

@Repository
public interface CustomerRepo extends JpaRepository<LtzCtlCustomer, Long> {

	@Query("SELECT y FROM LtzCtlCustomer y LEFT JOIN FETCH y.serveur S LEFT JOIN FETCH S.serveur ")
	public List<LtzCtlCustomer> findAll();
	
	@Query("SELECT y FROM LtzCtlCustomer y LEFT JOIN FETCH y.serveur S LEFT JOIN FETCH S.serveur WHERE y.id=:id")
	public Optional<LtzCtlCustomer> findById(@Param("id") Long id);

	public List<LtzCtlCustomer> findByActif(Boolean actif);

	public LtzCtlCustomer findByT(@Param("id") String login);

}
