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

import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.service.IUsers;
import lym.com.api.service.commons.ResultAction;

@RestController
@CrossOrigin("*")
@RequestMapping("erp_manager/users")
public class UsersController {

	@Autowired
	private IUsers service;

	@GetMapping("/list")
	public List<LtzCtlUsers> getUsers() {
		return service.findAllUsers();
	}

	@GetMapping("/list_actif")
	public List<LtzCtlUsers> getUsers(Boolean actif) {
		return service.findAllUserActif(actif);
	}

	@GetMapping("/list_admin")
	public List<LtzCtlUsers> getUsersAdmin() {
		return service.findAllUserAdmin();
	}

	@GetMapping("/list_by_customer/{idCustomer}")
	public List<LtzCtlUsers> getUsersByCustomer(@PathVariable("idCustomer") Long idCustomer) {
		return service.findAllUserCustomer(idCustomer);
	}

	@GetMapping("/user/{id}")
	public LtzCtlUsers getUser(@PathVariable("id") long id) {
		Optional<LtzCtlUsers> re = service.getOneUser(id);
		if (re.isPresent()) {
			return re.get();
		} else
			return null;
	}

	@PostMapping("/save")
	public ResultAction<LtzCtlUsers> saveUser(@RequestBody LtzCtlUsers entity) {
		return service.saveOneUser(entity);
	}

	@PostMapping("/update")
	public ResultAction<LtzCtlUsers> updateUser(@RequestBody LtzCtlUsers entity) {
		return service.updateOneUser(entity);
	}

	@PostMapping("/update_many")
	public ResultAction<LtzCtlUsers> updateManyUser(@RequestBody List<LtzCtlUsers> entity) {
		return service.updateUsers(entity);
	}

	@PostMapping("/delete_many")
	public ResultAction<LtzCtlUsers> deleteManyUser(@RequestBody List<LtzCtlUsers> entity) {
		return service.deleteUsers(entity);
	}
}
