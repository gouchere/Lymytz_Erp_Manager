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

import lombok.Data;

/**
 *
 * @author LYMYTZ
 */
@Entity
@Table(name = "ltz_ctl_periodes")
@Data
public class LtzCtlPeriodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "ltz_ctl_periodes_id_seq", sequenceName = "ltz_ctl_periodes_id_seq_name")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "libele")
    private String libele;
    @Column(name = "description")
    private String description;
    @Column(name = "nb_mois")
    private Integer nbMois;
    @Column(name = "actif")
    private Boolean actif;   
    
    @JoinColumn(name = "remise", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LtzCtlRemises remise;

    public LtzCtlPeriodes() {
    }

    public LtzCtlPeriodes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbMois() {
        return nbMois;
    }

    public void setNbMois(Integer nbMois) {
        this.nbMois = nbMois;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
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
        if (!(object instanceof LtzCtlPeriodes)) {
            return false;
        }
        LtzCtlPeriodes other = (LtzCtlPeriodes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lymytz.entity.ctl.LtzCtlPeriodes[ id=" + id + " ]";
    }
    
}
