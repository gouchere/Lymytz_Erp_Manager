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
@Table(name = "ltz_ctl_services")
public class LtzCtlServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_services_id_seq", sequenceName = "ltz_ctl_services_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code_service")
	private String codeService;
	@Column(name = "designation")
	private String designation;
	@Column(name = "prix")
	private Double prix;
	@Column(name = "description")
	private String description;
	@Column(name = "groupe")
	private String groupe;
	@Column(name = "image")
	private String image;
	@Column(name = "to_sell")
	private Boolean toSell;
	@Column(name = "fonctionnalite")
	private String fonctionnalites;
	@Column(name = "categorie")
	private String categorie;
	@Column(name = "actif")
	private Boolean actif;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;

	@Transient
	private List<LtzCtlLiaisonService> servicesLies;

	public LtzCtlServices() {
		// TODO Auto-generated constructor stub
	}

	public LtzCtlServices(Long id) {
		this.id = id;
	}

	public LtzCtlServices(Long id, String codeService, String designation) {
		this(id);
		this.codeService = codeService;
		this.designation = designation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeService() {
		return codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrix() {
		return prix != null ? prix : 0d;
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

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public Boolean getToSell() {
		return toSell;
	}

	public void setToSell(Boolean toSell) {
		this.toSell = toSell;
	}

	public String getFonctionnalites() {
		return fonctionnalites;
	}

	public void setFonctionnalites(String fonctionnalites) {
		this.fonctionnalites = fonctionnalites;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
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

	public List<LtzCtlLiaisonService> getServicesLies() {
		return servicesLies;
	}

	public void setServicesLies(List<LtzCtlLiaisonService> servicesLies) {
		this.servicesLies = servicesLies;
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
		LtzCtlServices other = (LtzCtlServices) obj;
		return Objects.equals(id, other.id);
	}

}
