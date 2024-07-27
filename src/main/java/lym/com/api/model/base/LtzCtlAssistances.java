
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 *
 * @author LYMYTZ
 */
@Entity
@Table(name = "ltz_ctl_assistances")
@Data
public class LtzCtlAssistances implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "ltz_ctl_assistances_id_seq", sequenceName = "ltz_ctl_assistances_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "code_assistance")
	private String codeAssistance;
	@Column(name = "designation")
	private String designation;
	@Column(name = "prix")
	private Double prix;
	@Column(name = "description")
	private String description;
	@Column(name = "image")
	private String image;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "date_save")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSave;
	@Column(name = "date_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;

	public LtzCtlAssistances() {
	}

	public LtzCtlAssistances(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeAssistance() {
		return codeAssistance;
	}

	public void setCodeAssistance(String codeAssistance) {
		this.codeAssistance = codeAssistance;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof LtzCtlAssistances)) {
			return false;
		}
		LtzCtlAssistances other = (LtzCtlAssistances) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.lymytz.entity.ctl.LtzCtlAssistances[ id=" + id + " ]";
	}

}
