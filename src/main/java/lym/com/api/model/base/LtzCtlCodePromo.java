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

import lombok.Data;

/**
 *
 * @author LYMYTZ
 */
@Entity
@Table(name = "ltz_ctl_code_promo")
@Data
public class LtzCtlCodePromo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "ltz_ctl_code_promo_id_seq", sequenceName = "ltz_ctl_code_promo_id_seq_name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "debut_validite")
    @Temporal(TemporalType.DATE)
    private Date debutValidite;
    @Column(name = "fin_validite")
    @Temporal(TemporalType.DATE)
    private Date finValidite;
    @Column(name = "debut_remise")
    @Temporal(TemporalType.DATE)
    private Date debutRemise;
    @Column(name = "fin_remise")
    @Temporal(TemporalType.DATE)
    private Date finRemise;
    @Column(name = "actif")
    private Boolean actif;
    @Column(name = "date_save")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSave;
    @Column(name = "date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    
    @JoinColumn(name = "remise", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LtzCtlRemises remise;

    public LtzCtlCodePromo() {
    }

    public LtzCtlCodePromo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getDebutRemise() {
        return debutRemise;
    }

    public void setDebutRemise(Date debutRemise) {
        this.debutRemise = debutRemise;
    }

    public Date getFinRemise() {
        return finRemise;
    }

    public void setFinRemise(Date finRemise) {
        this.finRemise = finRemise;
    }

    public Boolean getActif() {
        return actif;
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
        if (!(object instanceof LtzCtlCodePromo)) {
            return false;
        }
        LtzCtlCodePromo other = (LtzCtlCodePromo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lymytz.entity.ctl.LtzCtlCodePromo[ id=" + id + " ]";
    }
    
}
