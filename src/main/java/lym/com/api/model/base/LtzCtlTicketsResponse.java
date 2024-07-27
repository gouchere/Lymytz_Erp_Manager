package lym.com.api.model.base;

import java.io.Serializable;
import java.util.Date;
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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lym.com.api.converter.LtzCtlTicketsConverter;
import lym.com.api.converter.LtzCtlUsersConverter;

@Entity
@Table(name = "ltz_ctl_tickets_response")
@Data
public class LtzCtlTicketsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ltz_ctl_response_id_seq", sequenceName = "ltz_ctl_response_id_seq_name")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "message")
	private String message;
	@Column(name = "is_read")
	private Boolean isRead;
	@Column(name = "date_response")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateResponse;
	@Column(name = "date_save")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSave;
	@Column(name = "date_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket", referencedColumnName = "id")
	@JsonSerialize(converter = LtzCtlTicketsConverter.class)
	private LtzCtlTickets ticket;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users", referencedColumnName = "id")
	@JsonSerialize(converter = LtzCtlUsersConverter.class)
	private LtzCtlUsers users;

	public LtzCtlTicketsResponse() {
		// TODO Auto-generated constructor stub
	}

	public LtzCtlTicketsResponse(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsRead() {
		return isRead != null ? isRead : false;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Date getDateResponse() {
		return dateResponse;
	}

	public void setDateResponse(Date dateResponse) {
		this.dateResponse = dateResponse;
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

	public LtzCtlTickets getTicket() {
		return ticket;
	}

	public void setTicket(LtzCtlTickets ticket) {
		this.ticket = ticket;
	}

	public LtzCtlUsers getUsers() {
		return users;
	}

	public void setUsers(LtzCtlUsers users) {
		this.users = users;
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
		LtzCtlTicketsResponse other = (LtzCtlTicketsResponse) obj;
		return Objects.equals(id, other.id);
	}

}
