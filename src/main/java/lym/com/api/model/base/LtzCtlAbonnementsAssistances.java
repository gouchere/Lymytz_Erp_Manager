/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lym.com.api.model.base;
import java.io.Serializable;

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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lym.com.api.converter.LtzCtlAbonnementConverter;
import lym.com.api.converter.LtzCtlAssistanceConverter;

/**
 *
 * @author LYMYTZ
 */
@Entity
@Table(name = "ltz_ctl_abonnements_assistances")
@Data
public class LtzCtlAbonnementsAssistances implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "ltz_ctl_abonnements_assistances_id_seq", sequenceName = "ltz_ctl_abonnements_assistances_id_seq_name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "actif")
    private Boolean actif;
    
    @JoinColumn(name = "abonnement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(converter = LtzCtlAbonnementConverter.class)
    private LtzCtlAbonnements abonnement;
    @JoinColumn(name = "assistance", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(converter = LtzCtlAssistanceConverter.class)
    private LtzCtlAssistances assistance;

    public LtzCtlAbonnementsAssistances() {
    }

    public LtzCtlAbonnementsAssistances(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public LtzCtlAbonnements getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(LtzCtlAbonnements abonnement) {
        this.abonnement = abonnement;
    }

    public LtzCtlAssistances getAssistance() {
        return assistance;
    }

    public void setAssistance(LtzCtlAssistances assistance) {
        this.assistance = assistance;
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
        if (!(object instanceof LtzCtlAbonnementsAssistances)) {
            return false;
        }
        LtzCtlAbonnementsAssistances other = (LtzCtlAbonnementsAssistances) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lymytz.entity.ctl.LtzCtlAbonnementsAssistances[ id=" + id + " ]";
    }
    
}
