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

import lym.com.api.model.base.LtzCtlTicketsResponse;
import lym.com.api.service.ITicketsResponses;
import lym.com.api.service.commons.ResultAction;

@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/tickets_response")
public class TicketsResponseController {

	@Autowired
	private ITicketsResponses service;

	
	@GetMapping("/list_by_tiket/{idTicket}")
	public List<LtzCtlTicketsResponse> getUsersByCustomer(@PathVariable("idTicket") Long idTicket) {
		return service.findByTickets(idTicket);
	}

	@GetMapping("/ticket_response/{id}")
	public LtzCtlTicketsResponse getOneTicket(@PathVariable("id") long id) {
		Optional<LtzCtlTicketsResponse> re = service.findOne(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	public ResultAction<LtzCtlTicketsResponse> saveUser(@RequestBody LtzCtlTicketsResponse entity) {
		return service.saveOne(entity);
	}

	@PostMapping("/update")
	public ResultAction<LtzCtlTicketsResponse> updateUser(@RequestBody LtzCtlTicketsResponse entity) {
		return service.updateOne(entity);
	}

	@PostMapping("/update_many")
	public ResultAction<LtzCtlTicketsResponse> updateManyUser(@RequestBody List<LtzCtlTicketsResponse> entity) {
		return service.updateMany(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlTicketsResponse> deleteManyUser(@RequestBody List<LtzCtlTicketsResponse> entity) {
		return service.deleteMany(entity);
	}
}
