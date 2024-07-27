package lym.com.api.model.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lym.com.api.model.base.LtzCtlServices;

public class ServiceEntityBuilder {
	public static final String colonnes = "y.id, y.code_service, y.designation, y.prix, y.description, y.groupe, y.to_sell, "
			+ "y.fonctionnalite, y.categorie, y.actif, y.date_save, y.date_update ";

	public ServiceEntityBuilder() {
		// TODO Auto-generated constructor stub
	}

	public static LtzCtlServices buildFromObject(Object[] line) {
		LtzCtlServices re = new LtzCtlServices();
		if (line != null) {
			re.setId((line[1]!=null)?((BigInteger)line[1]).longValue():-1);
			re.setCodeService((String)line[2]);
			re.setDesignation((String)line[3]);
			re.setPrix(line[4]!=null?(Double)line[4]:0d);
			re.setDescription((String)line[5]);
			re.setGroupe((String)line[6]);
			re.setToSell(line[7]!=null?(Boolean)line[7]:false);
			re.setFonctionnalites((String)line[8]);
			re.setCategorie((String)line[9]);
			re.setActif(line[10]!=null?(Boolean)line[10]:false);
			re.setDateSave(line[10]!=null?(Date)line[11]:new Date());
			re.setDateUpdate(line[12]!=null?(Date)line[12]:new Date());
			
		}
		return re;
	}
	
	public static List<LtzCtlServices> buildListFromObject(List<Object[]> list) {
		List<LtzCtlServices> re = new ArrayList<>();
		if (list != null) {
			for(Object[] line:list)
			re.add(buildFromObject(line));
		}
		return re;
	}

}
