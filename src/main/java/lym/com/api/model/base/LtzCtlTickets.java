package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lym.com.api.converter.LtzCtlUsersConverter;

@Entity
@Table(name = "ltz_ctl_tickets")
@Data
public class LtzCtlTickets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ltz_ctl_tickets_id_seq", sequenceName = "ltz_ctl_tickets_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "objet")
	private String objet;
	@Column(name = "message")
	private String message;
	@Column(name = "statut")
	private String statut = "O"; // O=open C=Close
	@Column(name = "date_ticket")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTicket;
	@Column(name = "date_close")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateClose;
	@Column(name = "date_save")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSave;
	@Column(name = "date_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;

	@JoinColumn(name = "users", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonSerialize(converter = LtzCtlUsersConverter.class)
	private LtzCtlUsers users;

	@Transient
	private List<LtzCtlTicketsResponse> responses;

	public LtzCtlTickets() {
		// TODO Auto-generated constructor stub
	}

	public LtzCtlTickets(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDateTicket() {
		return dateTicket;
	}

	public void setDateTicket(Date dateTicket) {
		this.dateTicket = dateTicket;
	}

	public Date getDateClose() {
		return dateClose;
	}

	public void setDateClose(Date dateClose) {
		this.dateClose = dateClose;
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

	public LtzCtlUsers getUsers() {
		return users;
	}

	public void setUsers(LtzCtlUsers users) {
		this.users = users;
	}

	public List<LtzCtlTicketsResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<LtzCtlTicketsResponse> responses) {
		this.responses = responses;
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
		LtzCtlTickets other = (LtzCtlTickets) obj;
		return Objects.equals(id, other.id);
	}

}
