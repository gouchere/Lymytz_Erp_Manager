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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "ltz_ctl_customer")
@Entity
@Data
@NamedQuery(name = "LtzCtlCustomer.findByT", query = "SELECT l FROM LtzCtlCustomer l WHERE l.id = :id")
public class LtzCtlCustomer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_customer_id_seq", sequenceName = "ltz_ctl_customer_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "raison_social")
	private String raisonSocial;
	@Column(name = "pays")
	private String pays;
	@Column(name = "ville")
	private String ville;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;
	@Column(name = "adresse")
	private String adresse;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "id_client")
	private Long idClient;

	@OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
	private LtzCtlCustomerServeur serveur;

	public LtzCtlCustomer() {
	}

	public LtzCtlCustomer(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRaisonSocial() {
		return raisonSocial;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getIdClient() {
		return idClient;
	} 

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public LtzCtlCustomerServeur getServeur() {
		return serveur;
	}

	public void setServeur(LtzCtlCustomerServeur serveur) {
		this.serveur = serveur;
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
		LtzCtlCustomer other = (LtzCtlCustomer) obj;
		return Objects.equals(id, other.id);
	}

}
