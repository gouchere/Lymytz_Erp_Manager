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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "ltz_ctl_customer_adress")
@Entity
public class LtzCtlCustomerAdress implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "ltz_ctl_customer_adress_id_seq")
	private Long id;
	@Column(name = "indicatif_tel")
	private String indicatifTel;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "num_fax")
	private String numFax;
	
	@JoinColumn(name = "client", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private LtzCtlCustomer client;

	public LtzCtlCustomerAdress() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndicatifTel() {
		return indicatifTel;
	}

	public void setIndicatifTel(String indicatifTel) {
		this.indicatifTel = indicatifTel;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNumFax() {
		return numFax;
	}

	public void setNumFax(String numFax) {
		this.numFax = numFax;
	}

	public LtzCtlCustomer getClient() {
		return client;
	}

	public void setClient(LtzCtlCustomer client) {
		this.client = client;
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
		LtzCtlCustomerAdress other = (LtzCtlCustomerAdress) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "LtzCtlCustomerAdress [id=" + id + ", indicatifTel=" + indicatifTel + ", telephone=" + telephone
				+ ", numFax=" + numFax + "]";
	}

}
