package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ltz_ctl_ressources")
public class LtzCtlRessources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_ressources_id_seq", sequenceName = "ltz_ctl_ressources_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "ressource_name")
	private String ressourceName;
	@Column(name = "ressource_type")
	private String ressourceType;
	@Column(name = "description")
	private String description;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;

	@JoinColumn(name = "parent", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlRessources parent;

	public LtzCtlRessources() {
	}

	public LtzCtlRessources(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRessourceName() {
		return ressourceName;
	}

	public void setRessourceName(String ressourceName) {
		this.ressourceName = ressourceName;
	}

	public String getRessourceType() {
		return ressourceType;
	}

	public void setRessourceType(String ressourceType) {
		this.ressourceType = ressourceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Date getDateSave() {
		return dateSave;
	}

	public void setDateSave(Date dateSave) {
		this.dateSave = dateSave;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public LtzCtlRessources getParent() {
		return parent;
	}

	public void setParent(LtzCtlRessources parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LtzCtlRessources other = (LtzCtlRessources) obj;
		return Objects.equals(id, other.id);
	}

}
