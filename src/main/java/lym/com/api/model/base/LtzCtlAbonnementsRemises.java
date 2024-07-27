/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lym.com.api.model.base;
import java.io.Serializable;
import java.util.Date;

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
import lym.com.api.converter.LtzCtlAbonnementConverter;

/**
 *
 * @author LYMYTZ
 */
@Entity
@Table(name = "ltz_ctl_abonnements_remises")
@Data
public class LtzCtlAbonnementsRemises implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "ltz_ctl_abonnements_remises_id_seq", sequenceName = "ltz_ctl_abonnements_remises_id_seq_name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "debut_validite")
    @Temporal(TemporalType.DATE)
    private Date debutValidite;
    @Column(name = "fin_validite")
    @Temporal(TemporalType.DATE)
    private Date finValidite;
    @Column(name = "actif")
    private Boolean actif;
    @Column(name = "date_save")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSave;
    @Column(name = "date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    
    @JoinColumn(name = "abonnement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(converter = LtzCtlAbonnementConverter.class)
    private LtzCtlAbonnements abonnement;
    @JoinColumn(name = "remise", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LtzCtlRemises remise;

    public LtzCtlAbonnementsRemises() {
    }

    public LtzCtlAbonnementsRemises(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDebutValidite() {
        return debutValidite;
    }

    public void setDebutValidite(Date debutValidite) {
        this.debutValidite = debutValidite;
    }

    public Date getFinValidite() {
        return finValidite;
    }

    public void setFinValidite(Date finValidite) {
        this.finValidite = finValidite;
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

    public LtzCtlAbonnements getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(LtzCtlAbonnements abonnement) {
        this.abonnement = abonnement;
    }

    public LtzCtlRemises getRemise() {
        return remise;
    }

    public void setRemise(LtzCtlRemises remise) {
        this.remise = remise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtzCtlAbonnementsRemises)) {
            return false;
        }
        LtzCtlAbonnementsRemises other = (LtzCtlAbonnementsRemises) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lymytz.entity.ctl.LtzCtlAbonnementsRemises[ id=" + id + " ]";
    }
    
}
