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
import lym.com.api.converter.LtzCtlServiceConverter;

/**
 *
 * @author LYMYTZ
 */
@Entity
@Table(name = "ltz_ctl_abonnements_services")
@Data
public class LtzCtlAbonnementsServices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "ltz_ctl_abonnements_services_id_seq", sequenceName = "ltz_ctl_abonnements_services_id_seq_name")
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
    @JoinColumn(name = "service", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(converter = LtzCtlServiceConverter.class)
    private LtzCtlServices service;

    public LtzCtlAbonnementsServices() {
    }

    public LtzCtlAbonnementsServices(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActif() {
        return actif!=null?actif:false;
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

    public LtzCtlServices getService() {
        return service;
    }

    public void setService(LtzCtlServices service) {
        this.service = service;
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
        if (!(object instanceof LtzCtlAbonnementsServices)) {
            return false;
        }
        LtzCtlAbonnementsServices other = (LtzCtlAbonnementsServices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lymytz.entity.ctl.LtzCtlAbonnementsServices[ id=" + id + " ]";
    }
    
}
