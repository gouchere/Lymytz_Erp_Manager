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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lym.com.api.converter.LtzCtlCustomerConverter;
import lym.com.api.converter.LtzCtlNiveauConverter;
import lym.com.api.converter.LtzCtlUsersConverter;

@Table(name = "ltz_ctl_customer_user")
@Entity
@Data
public class LtzCtlCustomerUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ltz_ctl_customer_user_id_seq", sequenceName = "ltz_ctl_customer_user_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "date_save")
	private Date dateSave;
	@Column(name = "date_update")
	private Date dateUpdate;
	
	@JoinColumn(name = "id_customer", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlCustomerConverter.class)
	private LtzCtlCustomer idCustomer;
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlUsersConverter.class)
	private LtzCtlUsers idUser;
	
	public LtzCtlCustomerUser() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LtzCtlCustomer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(LtzCtlCustomer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public LtzCtlUsers getIdUser() {
		return idUser;
	}

	public void setIdUser(LtzCtlUsers idUser) {
		this.idUser = idUser;
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
		LtzCtlCustomerUser other = (LtzCtlCustomerUser) obj;
		return Objects.equals(id, other.id);
	}
	

}
