package lym.com.api.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import lym.com.api.model.base.LtzCtlCustomer;
import lym.com.api.model.base.LtzCtlCustomerServeur;
import lym.com.api.model.base.LtzCtlServeurs;

public class LtzCtlCustomerConverter extends StdConverter<LtzCtlCustomer, LtzCtlCustomer> {

	@Override
	public LtzCtlCustomer convert(LtzCtlCustomer value) {
		// TODO Auto-generated method stub
		if (value != null) {
			LtzCtlCustomer result = new LtzCtlCustomer();
			result.setId(value.getId());
			result.setRaisonSocial(value.getRaisonSocial());
			result.setPays(value.getPays());
			result.setVille(value.getVille());
			result.setActif(value.getActif());
			result.setAdresse(value.getAdresse());
			result.setTelephone(value.getTelephone());
			result.setIdClient(value.getIdClient());
			if(value.getServeur()!=null) {
				result.setServeur(new LtzCtlCustomerServeur());
				result.getServeur().setId(value.getServeur().getId());
				result.getServeur().setIdSociete(value.getServeur().getIdSociete());
				if(value.getServeur().getServeur()!=null) {
					result.getServeur().setServeur(new LtzCtlServeurs());
					result.getServeur().getServeur().setId(value.getServeur().getServeur().getId());
					result.getServeur().getServeur().setActif(value.getServeur().getServeur().getActif());
					result.getServeur().getServeur().setAdresseIp(value.getServeur().getServeur().getAdresseIp());
					result.getServeur().getServeur().setLocalisation(value.getServeur().getServeur().getLocalisation());
					result.getServeur().getServeur().setPortWeb(value.getServeur().getServeur().getPortWeb());
					result.getServeur().getServeur().setNom(value.getServeur().getServeur().getNom());
				}
			}
			return result;
		}else return new LtzCtlCustomer();
	}

}
