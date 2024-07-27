package lym.com.api.model.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlCustomerServeur;
import lym.com.api.model.base.LtzCtlCustomerUser;
import lym.com.api.model.base.LtzCtlServeurs;
import lym.com.api.model.base.LtzCtlUsers;
import lym.com.api.service.commons.ConstantesManager;

public class CustomerUserEntityBuilder {
	private static final Logger logger = LoggerFactory.getLogger(CustomerUserEntityBuilder.class);
	public static final String colonnes = "y.id, y.id_user, y.id_customer, c.raison_social, c.adresse, c.id_client, cs.id AS id_cs, "
			+ "cs.id_societe, s.id AS id_s, s.adresse_ip, s.port_web ";

	public static LtzCtlCustomerUser buildFromObject(Object[] line) {
		LtzCtlCustomerUser re = new LtzCtlCustomerUser();
		try {
			LtzCtlUsers u;
			LtzCtlCustomer c;
			LtzCtlCustomerServeur cu;
			LtzCtlServeurs s;
			if (line != null?line.length>0:false) {
				Object id = line[0];
				re.setId(ConstantesManager.isNumeric(id)?((BigInteger)line[0]).longValue():-1);
				if(line.length>1? line[1]!=null:false) {
					u=new LtzCtlUsers();
					u.setId(ConstantesManager.isNumeric(line[1])?((BigInteger)line[1]).longValue():-1);
					re.setIdUser(u);
				}
				if(line.length>2? line[2]!=null:false) {
					c=new LtzCtlCustomer();
					c.setId(ConstantesManager.isNumeric(line[2])?((BigInteger)line[2]).longValue():-1);
					c.setRaisonSocial((String)line[3]);
					c.setAdresse((String)line[4]);
					c.setIdClient((line[5]!=null)?((BigInteger)line[5]).longValue():-1);
					if(line.length>6? line[6]!=null:false) {
						cu = new LtzCtlCustomerServeur();
						cu.setId(ConstantesManager.isNumeric(line[6])?((BigInteger)line[6]).longValue():-1);
						cu.setIdSociete(ConstantesManager.isNumeric(line[7])?((BigInteger)line[7]).longValue():-1);
						if(line.length>8? line[8]!=null:false) {
							s = new LtzCtlServeurs();
							s.setId(ConstantesManager.isNumeric(line[8])?((BigInteger)line[8]).longValue():-1);
							s.setAdresseIp((String)line[9]);
							s.setPortWeb((String)line[10]);
							cu.setServeur(s);
						}	
						c.setServeur(cu);
					}					
					re.setIdCustomer(c);
				}
				
			}
		}catch(Exception ex) {
			logger.error(CustomerUserEntityBuilder.class.getName(), ex);
		}
		return re;
	}

	public static LtzCtlCustomerUser buildOneFromObject(List<Object[]> list) {
		if (list != null?!list.isEmpty():false) {
			return buildFromObject(list.get(0));
		}
		return new LtzCtlCustomerUser();
	}

	public static List<LtzCtlCustomerUser> buildListFromObject(List<Object[]> list) {
		List<LtzCtlCustomerUser> re = new ArrayList<>();
		if (list != null) {
			for (Object[] line : list)
				re.add(buildFromObject(line));
		}
		return re;
	}
}
