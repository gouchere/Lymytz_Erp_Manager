package lym.com.api.repository.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lym.com.api.model.base.LtzCtlTickets;
import lym.com.api.model.base.LtzCtlTicketsResponse;

@Repository
public interface TicketResponseRepo extends JpaRepository<LtzCtlTicketsResponse, Long>{
	
	@Query("SELECT y FROM LtzCtlTicketsResponse y JOIN FETCH y.ticket JOIN FETCH y.users WHERE y.ticket=:ticket ORDER BY y.dateResponse ASC, y.id ASC")
	public List<LtzCtlTicketsResponse> findByTicket(@Param("ticket")LtzCtlTickets ticket);

}
