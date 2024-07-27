package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlTickets;
import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.repository.base.TicketRepo;
import lym.com.api.repository.base.TicketResponseRepo;
import lym.com.api.repository.jdbc.CustomJdbcRepo;
import lym.com.api.repository.jdbc.JdbcUserRepo;
import lym.com.api.service.ITickets;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedTickets implements ITickets {

	@Autowired
	private TicketRepo service;
	@Autowired
	private TicketResponseRepo serviceResponse;

	@Autowired
	JdbcUserRepo serviceJdbc;
	
	@Autowired
	CustomJdbcRepo<LtzCtlTickets> repository;

	public ManagedTickets() {

	}

	
	@Override
	public Optional<LtzCtlTickets> findOne(Long id) {
		Optional<LtzCtlTickets> re=service.findById(id);
		if(re.isPresent()) {
			re.get().setResponses(serviceResponse.findByTicket(re.get()));
		}
		return re;
	}
	
	@Override
	public List<LtzCtlTickets> findByUsers(Long idUser) {
		return service.findByUsers(new LtzCtlUsers(idUser));
	}
	
	@Override
	public List<LtzCtlTickets> findTicketsByCustomer(Long idCustomer){
		String query="select t.*, u.* from ltz_ctl_tickets t inner join ltz_ctl_users u on u.id=t.users "
				+ "inner join ltz_ctl_customer_user cu on u.id=cu.id_user "
				+ "WHERE cu.id_customer= "+idCustomer;
		List<LtzCtlTickets> re=repository.loadDataWithJdbc(LtzCtlTickets.class, query);
		return re ;
	}
	
	/*Renvoie les id des tickets sur lesquelles on a des reponse non lu*/
	public List<Long> findIdTicketsNonLue(Long idUsers){
		if(idUsers!=null) {
		return service.findIdTicketsNonLue(idUsers);
		}else return new ArrayList<Long>();
		
	}
	
	/*Renvoie les id des tickets sur lesquelles on a des reponse non lu*/
	public List<Long> findIdTicketsNonLueFromUser(Long idUsers){
		if(idUsers!=null) {
		return service.findIdTicketsNonLueByUsers(idUsers);
		}else return new ArrayList<Long>();
		
	}
	
	/*
	 * Renvoie les id des tickets d'un customer sur lesquelles on a des reponse non
	 * lu
	 */
	public List<Long> findIdTicketsNonLueFromCustomer(Long idCustomer, Long idUsers){
		if(idCustomer!=null) {
		return service.findIdTicketsNonLueByCustomer(idCustomer, idUsers);
		}else return new ArrayList<Long>();
	}
	
	@Override
	public ResultAction<LtzCtlTickets> saveOne(LtzCtlTickets entity) {
		ResultAction<LtzCtlTickets> re = controleData(entity);
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
	public ResultAction<LtzCtlTickets> updateOne(LtzCtlTickets entity) {
		ResultAction<LtzCtlTickets> re = controleUpdateData(entity);
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
	public ResultAction<LtzCtlTickets> updateMany(List<LtzCtlTickets> listEntity) {
		ResultAction<LtzCtlTickets> re;
		List<LtzCtlTickets> l = new ArrayList<>();
		for (LtzCtlTickets y : listEntity) {
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
	public ResultAction<LtzCtlTickets> deleteOn(LtzCtlTickets entity) {
		ResultAction<LtzCtlTickets> re;
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
	public ResultAction<LtzCtlTickets> deleteMany(List<LtzCtlTickets> listEntity) {
		ResultAction<LtzCtlTickets> re;
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
	public ResultAction<LtzCtlTickets> controleData(LtzCtlTickets entity) {
		ResultAction<LtzCtlTickets> re;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getObjet())) {
			re = new ResultAction<>(false, -1, "Attribut objet IS NULL", "Indiquez l'objet de votre ticket");
		} else if (!ConstantesManager.isString(entity.getMessage())) {
			re = new ResultAction<>(false, -1, "Attribut message IS NULL", "Vous devez entrer sotre message");
		}else if(entity.getUsers()==null) {
			re = new ResultAction<>(false, -1, "Attribut users IS NULL", "L'auteur du ticket n'a pas été trouvé");
		}else if(!ConstantesManager.isLong(entity.getUsers().getId())) {
			re = new ResultAction<>(false, -1, "Attribut users.id IS NULL", "L'auteur du ticket n'a pas été trouvé");
		}else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlTickets> controleUpdateData(LtzCtlTickets entity) {
		ResultAction<LtzCtlTickets> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

}
