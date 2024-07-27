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
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lym.com.api.converter.LtzCtlNiveauConverter;

@Data
@Entity
@Table(name = "ltz_ctl_users")
public class LtzCtlUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_users_id_seq", sequenceName = "ltz_ctl_users_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "email")
	private String email;
	@Column(name = "code_user")
	private String codeUser;
	@Column(name = "photos")
	private String photos;
	@Column(name = "mot_de_passe")
	private String motDePasse;
	@Column(name = "salt")
	private String salt;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;

	@JoinColumn(name = "niveau", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlNiveauConverter.class)
	private LtzCtlNiveaux niveau;
	
//	@OneToOne(mappedBy = "idUser", fetch = FetchType.LAZY)
	@Transient
	private LtzCtlCustomerUser compte;

	public LtzCtlUsers() {
	}

	public LtzCtlUsers(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public LtzCtlNiveaux getNiveau() {
		return niveau;
	}

	public void setNiveau(LtzCtlNiveaux niveau) {
		this.niveau = niveau;
	}

	public Boolean getActif() {
		return actif!=null?actif:false;
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
	
	public LtzCtlCustomerUser getCompte() {
		return compte;
	}
	
	public void setCompte(LtzCtlCustomerUser compte) {
		this.compte = compte;
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
		LtzCtlUsers other = (LtzCtlUsers) obj;
		return Objects.equals(id, other.id);
	}

}
