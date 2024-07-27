package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlTickets;
import lym.com.api.model.base.LtzCtlTicketsResponse;
import lym.com.api.repository.base.TicketResponseRepo;
import lym.com.api.service.ITicketsResponses;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedTicketsResponse implements ITicketsResponses {

	@Autowired
	private TicketResponseRepo service;

	@Override
	public Optional<LtzCtlTicketsResponse> findOne(Long id) {
		return service.findById(id);
	}

	@Override
	public List<LtzCtlTicketsResponse> findByTickets(Long idTicket) {
		return service.findByTicket(new LtzCtlTickets(idTicket));
	}

	@Override
	public ResultAction<LtzCtlTicketsResponse> saveOne(LtzCtlTicketsResponse entity) {
		ResultAction<LtzCtlTicketsResponse> re = controleData(entity);
		if (re.isResult()) {
			try {
				entity = service.save(entity);
				re = new ResultAction<>(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
				re.setEntity(entity);
			} catch (Exception e) {
				re = new ResultAction<>(false);
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setCodeResult("Internal Server Error");
			}
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlTicketsResponse> updateOne(LtzCtlTicketsResponse entity) {
		ResultAction<LtzCtlTicketsResponse> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				entity = service.save(entity);
				re = new ResultAction<>(true);
				re.setMessage("Succes");
				re.setCodeInfo(200);
				re.setEntity(entity);
			} catch (Exception e) {
				re = new ResultAction<>(false);
				re.setMessage(e.getMessage());
				re.setCodeInfo(-1);
				re.setCodeResult("Internal Server Error");
			}
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlTicketsResponse> updateMany(List<LtzCtlTicketsResponse> listEntity) {
		ResultAction<LtzCtlTicketsResponse> re;
		List<LtzCtlTicketsResponse> l = new ArrayList<>();
		for (LtzCtlTicketsResponse y : listEntity) {
			re = controleUpdateData(y);
			if (!re.isResult()) {
				l.add(y);
			}
		}
		listEntity.removeAll(l);
		try {
			service.saveAll(listEntity);
			re = new ResultAction<>(true);
			re.setMessage("Succes");
			re.setCodeInfo(200);
			re.setListEntity(l);
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setMessage(e.getMessage());
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlTicketsResponse> deleteOn(LtzCtlTicketsResponse entity) {
		ResultAction<LtzCtlTicketsResponse> re;
		try {
			service.delete(entity);
			re = new ResultAction<>(true);
			re.setCodeInfo(200);
			re.setCodeResult("");
			re.setMessage("Succes");
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
			re.setMessage(e.getMessage());
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlTicketsResponse> deleteMany(List<LtzCtlTicketsResponse> listEntity) {
		ResultAction<LtzCtlTicketsResponse> re;
		try {
			service.deleteAll(listEntity);
			re = new ResultAction<>(true);
			re.setCodeInfo(200);
			re.setCodeResult("");
			re.setMessage("Succes");
		} catch (Exception e) {
			re = new ResultAction<>(false);
			re.setCodeInfo(-1);
			re.setCodeResult("Internal Server Error");
			re.setMessage(e.getMessage());
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlTicketsResponse> controleData(LtzCtlTicketsResponse entity) {
		ResultAction<LtzCtlTicketsResponse> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getMessage())) {
			re = new ResultAction<>(false, -1, "Attribut message IS NULL", "Vous devez entrer sotre message");
		} else if (entity.getUsers() == null) {
			re = new ResultAction<>(false, -1, "Attribut users IS NULL", "L'auteur du ticket n'a pas été trouvé");
		} else if (!ConstantesManager.isLong(entity.getUsers().getId())) {
			re = new ResultAction<>(false, -1, "Attribut users.id IS NULL", "L'auteur du ticket n'a pas été trouvé");
		} else if (entity.getTicket() == null) {
			re = new ResultAction<>(false, -1, "Attribut ticket IS NULL", "La source de votre message n'existe pas");
		} else if (!ConstantesManager.isLong(entity.getTicket().getId())) {
			re = new ResultAction<>(false, -1, "Attribut ticket.id IS NULL", "La source de votre message n'existe pas");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlTicketsResponse> controleUpdateData(LtzCtlTicketsResponse entity) {
		ResultAction<LtzCtlTicketsResponse> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

}
