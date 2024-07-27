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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lym.com.api.converter.LtzCtlNiveauConverter;

@Data
@Entity
@Table(name = "ltz_ctl_autorisations")
public class LtzCtlAutorisations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_autorisations_id_seq", sequenceName = "ltz_ctl_autorisations_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Column(name = "acces")
	private Boolean acces;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;

	@JoinColumn(name = "niveau", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlNiveauConverter.class)
	private LtzCtlNiveaux niveau;
	@JoinColumn(name = "ressource", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlRessources ressource;

	public LtzCtlAutorisations() {
	}

	public LtzCtlAutorisations(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Boolean getAcces() {
		return acces!=null?acces:false;
	}

	public void setAcces(Boolean acces) {
		this.acces = acces;
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

	public LtzCtlNiveaux getNiveau() {
		return niveau;
	}

	public void setNiveau(LtzCtlNiveaux niveau) {
		this.niveau = niveau;
	}

	public LtzCtlRessources getRessource() {
		return ressource;
	}

	public void setRessource(LtzCtlRessources ressource) {
		this.ressource = ressource;
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
		LtzCtlAutorisations other = (LtzCtlAutorisations) obj;
		return Objects.equals(id, other.id);
	}

}
