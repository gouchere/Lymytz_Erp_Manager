package lym.com.api.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlTickets;
import lym.com.api.model.base.LtzCtlUsers;

@Repository
public interface TicketRepo extends JpaRepository<LtzCtlTickets, Long>{
	
	@Query("SELECT y FROM LtzCtlTickets y JOIN FETCH y.users u JOIN FETCH u.niveau WHERE y.id=:id")
	public Optional<LtzCtlTickets> findById(@Param("id")Long id);
	
	@Query("SELECT y FROM LtzCtlTickets y JOIN FETCH y.users u JOIN FETCH u.niveau WHERE y.users=:users")
	public List<LtzCtlTickets> findByUsers(@Param("users")LtzCtlUsers users);
	
	@Query(value="SELECT DISTINCT t.id FROM ltz_ctl_tickets t INNER JOIN ltz_ctl_users u on u.id=t.users "
			+ "INNER JOIN ltz_ctl_tickets_response r ON r.ticket=t.id "
			+ "WHERE r.users != :users AND r.is_read IS FALSE", nativeQuery = true) 
	public List<Long> findIdTicketsNonLue(@Param("users")Long idUser);
	
	@Query(value="SELECT DISTINCT t.id FROM ltz_ctl_tickets t INNER JOIN ltz_ctl_users u on u.id=t.users "
			+ "INNER JOIN ltz_ctl_tickets_response r ON r.ticket=t.id "
			+ "WHERE u.id=:id AND r.users != :id AND r.is_read IS FALSE", nativeQuery = true) 
	public List<Long> findIdTicketsNonLueByUsers(@Param("id")Long idUser);
	
	@Query(value="SELECT DISTINCT t.id FROM ltz_ctl_tickets t INNER JOIN ltz_ctl_users u on u.id=t.users "
			+ "INNER JOIN ltz_ctl_customer_user cu ON cu.id_user=u.id "
			+ "INNER JOIN ltz_ctl_tickets_response r ON r.ticket=t.id "
			+ "WHERE cu.id_customer=:customer AND r.users != :users AND r.is_read IS FALSE", nativeQuery = true)
	public List<Long> findIdTicketsNonLueByCustomer(@Param("customer")Long idCustomer, @Param("users")Long idUsers);

}
