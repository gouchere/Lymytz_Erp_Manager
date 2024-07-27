package lym.com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lym.com.api.model.base.LtzCtlTickets;
import lym.com.api.service.ITickets;
import lym.com.api.service.commons.ResultAction;

@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/tickets")
public class TicketsController {

	@Autowired
	private ITickets service;

	@GetMapping("/list_by_user/{idUser}")
	public List<LtzCtlTickets> getUsersByCustomer(@PathVariable("idUser") Long idUser) {
		return service.findByUsers(idUser);
	}

	@GetMapping("/ticket/{id}")
	public LtzCtlTickets getOneTicket(@PathVariable("id") long id) {
		Optional<LtzCtlTickets> re = service.findOne(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@GetMapping("/list_by_customer/{id}")
	public List<LtzCtlTickets> getTicketsByCustomer(@PathVariable("id") long idCustomer) {
		List<LtzCtlTickets> re = service.findTicketsByCustomer(idCustomer);
		return re;
	}

	@GetMapping("/list_id_tickets_non_lu/{user}")
	public List<Long> findIdTicketsNonLue(@PathVariable("user")Long idUsers) {
		return service.findIdTicketsNonLueFromUser(idUsers);
	}

	@GetMapping("/list_id_tickets_non_lu_user/{id}")
	public List<Long> findIdTicketsNonLueFromUser(@PathVariable("id")Long idUsers) {
		return service.findIdTicketsNonLueFromUser(idUsers);
	}

	@GetMapping("/list_id_tickets_non_lu_customer/{customer}/{user}")
	public List<Long> findIdTicketsNonLueFromCustomer(@PathVariable("customer")Long idCustomer, @PathVariable("user")Long idUsers) {
		return service.findIdTicketsNonLueFromCustomer(idCustomer, idUsers);
	}

	@PostMapping("/save")
	public ResultAction<LtzCtlTickets> saveUser(@RequestBody LtzCtlTickets entity) {
		return service.saveOne(entity);
	}

	@PostMapping("/update")
	public ResultAction<LtzCtlTickets> updateUser(@RequestBody LtzCtlTickets entity) {
		return service.updateOne(entity);
	}

	@PostMapping("/update_many")
	public ResultAction<LtzCtlTickets> updateManyUser(@RequestBody List<LtzCtlTickets> entity) {
		return service.updateMany(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlTickets> deleteManyUser(@RequestBody List<LtzCtlTickets> entity) {
		return service.deleteMany(entity);
	}
}
