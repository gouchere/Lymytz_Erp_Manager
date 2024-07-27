package lym.com.api.model.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import lym.com.api.model.base.LtzCtlAutorisations;
import lym.com.api.model.base.LtzCtlNiveaux;
import lym.com.api.model.base.LtzCtlRessources;

public class AutorisationEntityBuilder {
	public static final String colonnes = "a.id, a.acces, y.id idR, y.ressource_name, y.description, a.niveau ";

	public AutorisationEntityBuilder() {
		// TODO Auto-generated constructor stub
	}

	public static LtzCtlAutorisations buildFromObject(Object[] line, Long idNiveau) {
		LtzCtlAutorisations re = new LtzCtlAutorisations();
		LtzCtlRessources r;
		LtzCtlNiveaux n;
		if (line != null) {
			re.setId((line[0]!=null)?((BigInteger)line[0]).longValue():-1);
			re.setAcces(line[1]!=null?(Boolean)line[1]:false);
			if(line[2]!=null) {
				r=new LtzCtlRessources();
				r.setId((line[2]!=null)?((BigInteger)line[2]).longValue():-1);
				r.setRessourceName((String)line[3]);
				r.setDescription((String)line[4]);
				re.setRessource(r);
			}
				n=new LtzCtlNiveaux(idNiveau);
				re.setNiveau(n);
			
		}
		return re;
	}
	
	public static List<LtzCtlAutorisations> buildListFromObject(List<Object[]> list, Long idNiveau) {
		List<LtzCtlAutorisations> re = new ArrayList<>();
		if (list != null) {
			for(Object[] line:list)
			re.add(buildFromObject(line, idNiveau));
		}
		return re;
	}

}
