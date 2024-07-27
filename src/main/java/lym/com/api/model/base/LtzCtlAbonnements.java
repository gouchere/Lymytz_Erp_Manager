/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "ltz_ctl_abonnements")
@Data
public class LtzCtlAbonnements implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "ltz_ctl_abonnements_id_seq", sequenceName = "ltz_ctl_abonnements_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "date_abonnement")
	@Temporal(TemporalType.DATE)
	private Date dateAbonnement;
	@Column(name = "date_next_facture")
	@Temporal(TemporalType.DATE)
	private Date dateNextFacture;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "date_save")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSave;
	@Column(name = "date_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;
	
	@JoinColumn(name = "customer", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlCustomer customer;
	@JoinColumn(name = "licence", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlLicences licence;
	@JoinColumn(name = "periode_facturation", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlPeriodes periodeFacturation;

	@OneToMany(mappedBy = "abonnement", fetch = FetchType.LAZY)
	private List<LtzCtlAbonnementsRemises> remises;
	@OneToMany(mappedBy = "abonnement", fetch = FetchType.LAZY)
	private List<LtzCtlAbonnementsServices> services;
	@OneToMany(mappedBy = "abonnement", fetch = FetchType.LAZY)
	private List<LtzCtlAbonnementsAssistances> assistances;

	public LtzCtlAbonnements() {
	}

	public LtzCtlAbonnements(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateAbonnement() {
		return dateAbonnement;
	}

	public void setDateAbonnement(Date dateAbonnement) {
		this.dateAbonnement = dateAbonnement;
	}

	public Date getDateNextFacture() {
		return dateNextFacture;
	}

	public void setDateNextFacture(Date dateNextFacture) {
		this.dateNextFacture = dateNextFacture;
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

	public LtzCtlCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(LtzCtlCustomer customer) {
		this.customer = customer;
	}

	public LtzCtlLicences getLicence() {
		return licence;
	}

	public void setLicence(LtzCtlLicences licence) {
		this.licence = licence;
	}

	public LtzCtlPeriodes getPeriodeFacturation() {
		return periodeFacturation;
	}

	public void setPeriodeFacturation(LtzCtlPeriodes periodeFacturation) {
		this.periodeFacturation = periodeFacturation;
	}

	public List<LtzCtlAbonnementsRemises> getRemises() {
		return remises;
	}

	public void setRemises(List<LtzCtlAbonnementsRemises> remises) {
		this.remises = remises;
	}

	public List<LtzCtlAbonnementsServices> getServices() {
		return services;
	}

	public void setServices(List<LtzCtlAbonnementsServices> services) {
		this.services = services;
	}

	public List<LtzCtlAbonnementsAssistances> getAssistances() {
		return assistances;
	}

	public void setAssistances(List<LtzCtlAbonnementsAssistances> assistances) {
		this.assistances = assistances;
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
		if (!(object instanceof LtzCtlAbonnements)) {
			return false;
		}
		LtzCtlAbonnements other = (LtzCtlAbonnements) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.lymytz.entity.ctl.LtzCtlAbonnements[ id=" + id + " ]";
	}

}
