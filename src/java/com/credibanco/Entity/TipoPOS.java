package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.CascadeType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "tipo_POS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPOS.findAll", query = "SELECT t FROM TipoPOS t"),
    @NamedQuery(name = "TipoPOS.findByIdTipoPos", query = "SELECT t FROM TipoPOS t WHERE t.idTipoPos = :idTipoPos"),
    @NamedQuery(name = "TipoPOS.findByTipoPos", query = "SELECT t FROM TipoPOS t WHERE t.tipoPos = :tipoPos"),
    @NamedQuery(name = "TipoPOS.findByVisible", query = "SELECT t FROM TipoPOS t WHERE t.visible = :visible")})
public class TipoPOS implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTecnologia")
    private Collection<ReporteFallas> reporteFallasCollection;

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipo_Pos")
    private Integer idTipoPos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Pos")
    private String tipoPos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPOS")
    private Collection<SolicitudPos> solicitudPosCollection;

    public TipoPOS() {
    }

    /**
     * @param idTipoPos 
     */
    public TipoPOS( Integer idTipoPos ) {
        this.idTipoPos = idTipoPos;
    }

    /**
     * @param idTipoPos
     * @param tipoPos
     * @param visible 
     */
    public TipoPOS( Integer idTipoPos, String tipoPos, boolean visible ) {
        this.idTipoPos = idTipoPos;
        this.tipoPos = tipoPos;
        this.visible = visible;
    }

    /**
     * @return Integer
     */
    public Integer getIdTipoPos() {
        return idTipoPos;
    }

    /**
     * @param idTipoPos
     */
    public void setIdTipoPos( Integer idTipoPos ) {
        this.idTipoPos = idTipoPos;
    }

    /**
     * @return String
     */
    public String getTipoPos() {
        return tipoPos;
    }

    /**
     * @param tipoPos
     */
    public void setTipoPos( String tipoPos ) {
        this.tipoPos = tipoPos;
    }

    /**
     * @return boolean
     */
    public boolean getVisible() {
        return visible;
    }

    /**
     * @param visible 
     */
    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<SolicitudPos> getSolicitudPosCollection() {
        return solicitudPosCollection;
    }

    /**
     * @param solicitudPosCollection
     */
    public void setSolicitudPosCollection( Collection<SolicitudPos> solicitudPosCollection ) {
        this.solicitudPosCollection = solicitudPosCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPos != null ? idTipoPos.hashCode() : 0);
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof TipoPOS ))
            return false;
        TipoPOS other = ( TipoPOS ) object;

        if (( this.idTipoPos == null && other.idTipoPos != null ) || ( this.idTipoPos != null && !this.idTipoPos.equals( other.idTipoPos )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.TipoPOS[ idTipoPos=" + idTipoPos + " ]";
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<ReporteFallas> getReporteFallasCollection() {
        return reporteFallasCollection;
    }

    /**
     * @param reporteFallasCollection 
     */
    public void setReporteFallasCollection(Collection<ReporteFallas> reporteFallasCollection) {
        this.reporteFallasCollection = reporteFallasCollection;
    }
}