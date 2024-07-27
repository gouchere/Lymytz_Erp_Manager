package lym.com.api.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlUsers;

@Repository
public interface UsersRepo extends JpaRepository<LtzCtlUsers, Long> {

	public List<LtzCtlUsers> findByActif(Boolean actif);

	@Query("SELECT y FROM LtzCtlUsers y LEFT JOIN FETCH y.niveau WHERE y.codeUser=:code OR y.email=:code")
	public Optional<LtzCtlUsers> findByCodeUser(@Param("code") String code);
	
	@Query("SELECT y FROM LtzCtlUsers y LEFT JOIN FETCH y.niveau ORDER BY y.nom, y.prenom")
	public List<LtzCtlUsers> findAll();
	
	@Query("SELECT y FROM LtzCtlUsers y LEFT JOIN FETCH y.niveau ORDER BY y.nom, y.prenom")
	public List<LtzCtlUsers> findUsersAdmin();
	
	@Query("SELECT y FROM LtzCtlUsers y LEFT JOIN FETCH y.niveau WHERE y.id=:id")
	public Optional<LtzCtlUsers> findById(@Param("id") Long id);

}
