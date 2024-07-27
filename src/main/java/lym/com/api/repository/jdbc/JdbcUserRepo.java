package lym.com.api.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lym.com.api.model.base.LtzCtlUsers;

@Service
public class JdbcUserRepo{
	
	@Autowired
	CustomJdbcRepo<LtzCtlUsers> repository;

	public List<LtzCtlUsers> findAllWhenNotCustomer() {
		String query="SELECT u.*, n.* FROM ltz_ctl_users u INNER JOIN ltz_ctl_niveaux n ON n.id=u.niveau "
				+ "LEFT JOIN ltz_ctl_customer_user cu ON cu.id_user=u.id WHERE cu.id_customer IS NULL";
		
		List<LtzCtlUsers> re=repository.loadDataWithJdbc(LtzCtlUsers.class, query);
		System.err.println(re.size()); 
		return re;
	}

	

}
