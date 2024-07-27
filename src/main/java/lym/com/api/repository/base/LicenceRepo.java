package lym.com.api.repository.base;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlLicences;

@Repository
public interface LicenceRepo extends JpaRepository<LtzCtlLicences, Long>{

	@Query("SELECT y FROM LtzCtlLicences y LEFT JOIN FETCH y.remise ORDER BY y.demo DESC")
	public List<LtzCtlLicences> findAll();
	
	@Query("SELECT y FROM LtzCtlLicences y LEFT JOIN FETCH y.remise WHERE y.actif=:actif ORDER BY y.demo DESC")
	public List<LtzCtlLicences> findByActif(@Param("actif") Boolean actif);
	
	@Query("SELECT y FROM LtzCtlLicences y LEFT JOIN FETCH y.remise WHERE y.id=:id ORDER BY y.demo DESC")
	public Optional<LtzCtlLicences> findById(@Param("id") Long id);

	public List<LtzCtlLicences> findByDemo(Boolean demo);
	
	@Query("SELECT y.licence FROM LtzCtlAbonnements y JOIN y.licence L LEFT JOIN FETCH L.remise "
			+ "WHERE y.customer.id = :customer")
	public Optional<LtzCtlLicences> findByCustomer(@Param("customer") Long customer);
	
}
