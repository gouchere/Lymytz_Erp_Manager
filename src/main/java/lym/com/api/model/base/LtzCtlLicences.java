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

@Table(name = "ltz_ctl_licences")
@Entity
@Data
public class LtzCtlLicences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_licences_id_seq", sequenceName = "ltz_ctl_licences_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code_licence")
	private String codeLicence;
	@Column(name = "nb_user")
	private Integer nbUser;
	@Column(name = "montant")
	private Double montant;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "demo")
	private Boolean demo;
	@Column(name = "duree_contrat")
	private Integer dureeContrat;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;
	
	@JoinColumn(name = "remise", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlRemises remise;

	public LtzCtlLicences() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeLicence() {
		return codeLicence;
	}

	public void setCodeLicence(String codeLicence) {
		this.codeLicence = codeLicence;
	}

	public Integer getNbUser() {
		return nbUser;
	}

	public void setNbUser(Integer nbUser) {
		this.nbUser = nbUser;
	}

	public Boolean getActif() {
		return actif != null ? actif : false;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Double getMontant() {
		return montant != null ? montant : 0;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
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

	public Boolean getDemo() {
		return demo != null ? demo : false;
	}

	public void setDemo(Boolean demo) {
		this.demo = demo;
	}

	public Integer getDureeContrat() {
		return dureeContrat;
	}

	public void setDureeContrat(Integer dureeContrat) {
		this.dureeContrat = dureeContrat;
	}

	public LtzCtlRemises getRemise() {
		return remise;
	}

	public void setRemise(LtzCtlRemises remise) {
		this.remise = remise;
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
		LtzCtlLicences other = (LtzCtlLicences) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "LtzCtlLicences [codeLicence=" + codeLicence + "]";
	}

}
