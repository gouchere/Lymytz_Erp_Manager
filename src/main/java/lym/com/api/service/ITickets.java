package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlTickets;
import lym.com.api.service.commons.ResultAction;

public interface ITickets {

	public Optional<LtzCtlTickets> findOne(Long idUsers);

	/*
	 * Renvoie les id des tickets lesquelles on a des reponses non lu pour un admin
	 */
	public List<Long> findIdTicketsNonLue(Long idUsers);

	/*
	 * Renvoie les id des tickets d'un users sur lesquelles on a des reponses non lu
	 */
	public List<Long> findIdTicketsNonLueFromUser(Long idUsers);

	/*
	 * Renvoie les id des tickets d'un customer sur lesquelles on a des reponse non
	 * lu
	 */
	public List<Long> findIdTicketsNonLueFromCustomer(Long idCustomer, Long idUsers);

	public List<LtzCtlTickets> findByUsers(Long idUsers);

	public List<LtzCtlTickets> findTicketsByCustomer(Long idCustomer);

	public ResultAction<LtzCtlTickets> saveOne(LtzCtlTickets entity);

	public ResultAction<LtzCtlTickets> updateOne(LtzCtlTickets entity);

	public ResultAction<LtzCtlTickets> updateMany(List<LtzCtlTickets> listEntity);

	public ResultAction<LtzCtlTickets> deleteOn(LtzCtlTickets entity);

	public ResultAction<LtzCtlTickets> deleteMany(List<LtzCtlTickets> listEntity);

	public ResultAction<LtzCtlTickets> controleData(LtzCtlTickets entity);

	public ResultAction<LtzCtlTickets> controleUpdateData(LtzCtlTickets entity);

}
