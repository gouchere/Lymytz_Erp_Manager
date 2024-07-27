package lym.com.api.service;

import java.util.List;
import java.util.Optional;

import lym.com.api.model.base.LtzCtlTicketsResponse;
import lym.com.api.service.commons.ResultAction;

public interface ITicketsResponses {

	public Optional<LtzCtlTicketsResponse> findOne(Long id);

	public List<LtzCtlTicketsResponse> findByTickets(Long idTicket);

	public ResultAction<LtzCtlTicketsResponse> saveOne(LtzCtlTicketsResponse entity);

	public ResultAction<LtzCtlTicketsResponse> updateOne(LtzCtlTicketsResponse entity);

	public ResultAction<LtzCtlTicketsResponse> updateMany(List<LtzCtlTicketsResponse> listEntity);

	public ResultAction<LtzCtlTicketsResponse> deleteOn(LtzCtlTicketsResponse entity);

	public ResultAction<LtzCtlTicketsResponse> deleteMany(List<LtzCtlTicketsResponse> listEntity);

	public ResultAction<LtzCtlTicketsResponse> controleData(LtzCtlTicketsResponse entity);

	public ResultAction<LtzCtlTicketsResponse> controleUpdateData(LtzCtlTicketsResponse entity);

}
