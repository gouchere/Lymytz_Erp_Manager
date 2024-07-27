package lym.com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.Data;
import lym.com.api.model.base.LtzCtlCodePromo;
import lym.com.api.repository.base.CodesPromoRepo;
import lym.com.api.service.ICodesPromo;
import lym.com.api.service.commons.ConstantesManager;
import lym.com.api.service.commons.ResultAction;

@Service
@Data
public class ManagedCodePromo implements ICodesPromo{

	@Autowired
	private CodesPromoRepo service;

	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	

	public ManagedCodePromo() {
		
	}

	@Override
	public Optional<LtzCtlCodePromo> getOneCodePromo(Long id) {
		Optional<LtzCtlCodePromo> re = service.findById(id);
		return re;
	}

	@Override
	public List<LtzCtlCodePromo> findAllCodesPromo() {
		return service.findAll();
	}

	@Override
	public List<LtzCtlCodePromo> findAllCodesPromoActif(Boolean actif) {
		return service.findByActif(actif);
	}

	@Override
	public ResultAction<LtzCtlCodePromo> saveOneCodePromo(LtzCtlCodePromo entity) {
		ResultAction<LtzCtlCodePromo> re = controleData(entity);
		if (re.isResult()) {
			try {
				entity.setId(null);
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
	public ResultAction<LtzCtlCodePromo> updateOneCodePromo(LtzCtlCodePromo entity) {
		ResultAction<LtzCtlCodePromo> re = controleUpdateData(entity);
		if (re.isResult()) {
			try {
				service.save(entity);
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
	public ResultAction<LtzCtlCodePromo> updateCodesPromo(List<LtzCtlCodePromo> listEntity) {
		ResultAction<LtzCtlCodePromo> re;
		List<LtzCtlCodePromo> l = new ArrayList<>();
		for (LtzCtlCodePromo y : listEntity) {
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
	public ResultAction<LtzCtlCodePromo> deleteOnCodePromo(LtzCtlCodePromo entity) {
		ResultAction<LtzCtlCodePromo> re;
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
	public ResultAction<LtzCtlCodePromo> deleteCodesPromo(List<LtzCtlCodePromo> listEntity) {
		ResultAction<LtzCtlCodePromo> re;
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
	public ResultAction<LtzCtlCodePromo> controleData(LtzCtlCodePromo entity) {
		ResultAction<LtzCtlCodePromo> re=null;
		if (entity == null) {
			re = new ResultAction<>(false, -1, "Entity IS NULL", "Aucune entité n'a été trouvé");
		} else if (!ConstantesManager.isString(entity.getCode())) {
			re = new ResultAction<>(false, -1, "Attribut code IS NULL", "Indiquez un code pour ce CodePromo d'accès");
		} else if (entity.getDebutValidite()==null) {
			re = new ResultAction<>(false, -1, "Attribut debutValidite IS NULL", "Indiquez la période de validité");
		} else if (entity.getFinValidite()==null) {
			re = new ResultAction<>(false, -1, "Attribut email IS NULL", "Indiquez la période de validité");
		} else {
			re = new ResultAction<>(true);
		}
		return re;
	}

	@Override
	public ResultAction<LtzCtlCodePromo> controleUpdateData(LtzCtlCodePromo entity) {
		ResultAction<LtzCtlCodePromo> re = controleData(entity);
		if (re.isResult()) {
			if (!ConstantesManager.isLong(entity.getId())) {
				re = new ResultAction<>(false, -1, "Attibut id IS NULL",
						"L'identifiant de l'objet n'a pas été trouvé ");
			}
		}
		return re;
	}

	@Override
	public void loadDataSource() {
		// TODO Auto-generated method stub
		
	}

}
