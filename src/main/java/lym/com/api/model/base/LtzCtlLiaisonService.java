package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lym.com.api.converter.LtzCtlServiceConverter;

@Data
@Entity
@Table(name = "ltz_ctl_liaison_service")
@NamedQueries({
	@NamedQuery(name = "LtzCtlLiaisonService.findByServicePrincipal", query = "SELECT y FROM LtzCtlLiaisonService y JOIN FETCH y.serviceLie JOIN FETCH y.servicePrincipal WHERE y.servicePrincipal=:service")
})
public class LtzCtlLiaisonService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_liaison_service_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "service_principal", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlServiceConverter.class)
	private LtzCtlServices servicePrincipal;
	@JoinColumn(name = "service_lie", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlServiceConverter.class)
	private LtzCtlServices serviceLie;

	public LtzCtlLiaisonService() {
		// TODO Auto-generated constructor stub
	}
	
	public LtzCtlLiaisonService(Long id) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LtzCtlServices getServicePrincipal() {
		return servicePrincipal;
	}

	public void setServicePrincipal(LtzCtlServices servicePrincipal) {
		this.servicePrincipal = servicePrincipal;
	}

	public LtzCtlServices getServiceLie() {
		return serviceLie;
	}

	public void setServiceLie(LtzCtlServices serviceLie) {
		this.serviceLie = serviceLie;
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
		LtzCtlLiaisonService other = (LtzCtlLiaisonService) obj;
		return Objects.equals(id, other.id);
	}

}
