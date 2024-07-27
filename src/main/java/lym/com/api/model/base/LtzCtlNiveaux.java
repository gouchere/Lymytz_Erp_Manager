package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "ltz_ctl_niveaux")
public class LtzCtlNiveaux implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_niveaux_id_seq", sequenceName = "ltz_ctl_niveaux_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "libele")
	private String libele;
	@Column(name = "grade")
	private String grade;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "defaut")
	private Boolean defaut;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;
	@Column(name = "publique")
	private Boolean publique;

	@Transient
	private List<LtzCtlAutorisations> autorisations;

	public LtzCtlNiveaux() {
	}

	public LtzCtlNiveaux(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Boolean getDefaut() {
		return defaut;
	}

	public void setDefaut(Boolean defaut) {
		this.defaut = defaut;
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

	public Boolean getPublique() {
		return publique != null ? publique : false;
	}

	public void setPublique(Boolean publique) {
		this.publique = publique;
	}

	public List<LtzCtlAutorisations> getAutorisations() {
		return autorisations;
	}

	public void setAutorisations(List<LtzCtlAutorisations> autorisations) {
		this.autorisations = autorisations;
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
		LtzCtlNiveaux other = (LtzCtlNiveaux) obj;
		return Objects.equals(id, other.id);
	}

}
