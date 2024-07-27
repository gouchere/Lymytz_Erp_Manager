/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lym.com.api.model.base;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;

import lombok.Data;
import lym.com.api.converter.LtzCtlCustomerConverter;

/**
 *
 * @author LYMYTZ
 */
@Entity
@Table(name = "ltz_ctl_customer_serveur")
@Data
public class LtzCtlCustomerServeur implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ltz_ctl_customer_serveur_id_seq", sequenceName = "ltz_ctl_customer_serveur_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "id_societe")
	private Long idSociete;
	@Basic(optional = false)
	@NotNull
	@Column(name = "admin_login")
	private String adminLogin;
	@Column(name = "mot_de_passe")
	private String motDePasse; 
	@Column(name = "adresse_erp")
	private String adresseErp; 

	@JoinColumn(name = "customer", referencedColumnName = "id")
	@OneToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlCustomerConverter.class)
	private LtzCtlCustomer customer;
	@JoinColumn(name = "serveur", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlServeurs serveur;

	public LtzCtlCustomerServeur() {
	}

	public LtzCtlCustomerServeur(Long id) {
		this.id = id;
	}

	public LtzCtlCustomerServeur(Long id, long idSociete, String adminLogin) {
		this.id = id;
		this.idSociete = idSociete;
		this.adminLogin = adminLogin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LtzCtlCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(LtzCtlCustomer customer) {
		this.customer = customer;
	}

	public LtzCtlServeurs getServeur() {
		return serveur;
	}

	public void setServeur(LtzCtlServeurs serveur) {
		this.serveur = serveur;
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
		if (!(object instanceof LtzCtlCustomerServeur)) {
			return false;
		}
		LtzCtlCustomerServeur other = (LtzCtlCustomerServeur) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.lymytz.ctl.entity.ctl.LtzCtlCustomerConInfo[ id=" + id + " ]";
	}

	public long getIdSociete() {
		return idSociete!=null?idSociete:0;
	}

	public void setIdSociete(long idSociete) {
		this.idSociete = idSociete;
	}

	public String getAdminLogin() {
		return adminLogin;
	}

	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getAdresseErp() {
		return adresseErp;
	}

	public void setAdresseErp(String adresseErp) {
		this.adresseErp = adresseErp;
	}

}
