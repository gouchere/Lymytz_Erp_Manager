package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ltz_ctl_ressource_response")
@Data
public class LtzCtlRessourceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "ltz_ctl_ressource_response_id_seq", sequenceName = "ltz_ctl_ressource_response_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id; 
	@Column(name = "nature")
	private String nature;
	@Column(name = "contenu")
	private String contenu;
	
	@ManyToOne
	@JoinColumn(name = "response", referencedColumnName = "id")
	private LtzCtlTicketsResponse response;
	
	public LtzCtlRessourceResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public LtzCtlRessourceResponse(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LtzCtlTicketsResponse getResponse() {
		return response;
	}

	public void setResponse(LtzCtlTicketsResponse response) {
		this.response = response;
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
		LtzCtlRessourceResponse other = (LtzCtlRessourceResponse) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
