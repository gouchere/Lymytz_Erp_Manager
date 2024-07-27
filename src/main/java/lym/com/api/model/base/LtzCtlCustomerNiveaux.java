package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ltz_ctl_customer_niveaux")
public class LtzCtlCustomerNiveaux implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_customer_niveaux_id_seq", sequenceName = "ltz_ctl_customer_niveaux_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LtzCtlNiveaux niveau;
	private LtzCtlCustomer customer;

	public LtzCtlCustomerNiveaux() {
	}

	public LtzCtlCustomerNiveaux(Long id) {
		this.id = id;
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

	public LtzCtlNiveaux getNiveau() {
		return niveau;
	}

	public void setNiveau(LtzCtlNiveaux niveau) {
		this.niveau = niveau;
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
		LtzCtlCustomerNiveaux other = (LtzCtlCustomerNiveaux) obj;
		return Objects.equals(id, other.id);
	}

}
