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
@Table(name = "Estados_General")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosGeneral.findAll", query = "SELECT e FROM EstadosGeneral e"),
    @NamedQuery(name = "EstadosGeneral.findByIdEstado", query = "SELECT e FROM EstadosGeneral e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "EstadosGeneral.findByTextoEstado", query = "SELECT e FROM EstadosGeneral e WHERE e.textoEstado = :textoEstado"),
    @NamedQuery(name = "EstadosGeneral.findByGrupoEstado", query = "SELECT e FROM EstadosGeneral e WHERE e.grupoEstado = :grupoEstado"),
    @NamedQuery(name = "EstadosGeneral.findByVisible", query = "SELECT e FROM EstadosGeneral e WHERE e.visible = :visible")})
public class EstadosGeneral implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<SolicitudMiPago> solicitudMiPagoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<SolicitudPos> solicitudPosCollection;

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_Estado")
    private Integer idEstado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Texto_Estado")
    private String textoEstado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Grupo_Estado")
    private int grupoEstado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<ReporteFallas> reporteFallasCollection;

    public EstadosGeneral() {
    }

    /**
     * @param idEstado
     */
    public EstadosGeneral( Integer idEstado ) {
        this.idEstado = idEstado;
    }

    /**
     * @param idEstado
     * @param textoEstado
     * @param grupoEstado
     * @param visible
     */
    public EstadosGeneral( Integer idEstado, String textoEstado, int grupoEstado, boolean visible ) {
        this.idEstado = idEstado;
        this.textoEstado = textoEstado;
        this.grupoEstado = grupoEstado;
        this.visible = visible;
    }

    /**
     * @return Integer
     */
    public Integer getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado
     */
    public void setIdEstado( Integer idEstado ) {
        this.idEstado = idEstado;
    }

    /**
     * @return String
     */
    public String getTextoEstado() {
        return textoEstado;
    }

    /**
     * @param textoEstado
     */
    public void setTextoEstado( String textoEstado ) {
        this.textoEstado = textoEstado;
    }

    /**
     * @return int
     */
    public int getGrupoEstado() {
        return grupoEstado;
    }

    /**
     * @param grupoEstado
     */
    public void setGrupoEstado( int grupoEstado ) {
        this.grupoEstado = grupoEstado;
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
    public Collection<ReporteFallas> getReporteFallasCollection() {
        return reporteFallasCollection;
    }

    /**
     * @param reporteFallasCollection
     */
    public void setReporteFallasCollection( Collection<ReporteFallas> reporteFallasCollection ) {
        this.reporteFallasCollection = reporteFallasCollection;
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
     * @return Collection
     */
    @XmlTransient
    public Collection<SolicitudMiPago> getSolicitudMiPagoCollection() {
        return solicitudMiPagoCollection;
    }

    /**
     * @param solicitudMiPagoCollection
     */
    public void setSolicitudMiPagoCollection( Collection<SolicitudMiPago> solicitudMiPagoCollection ) {
        this.solicitudMiPagoCollection = solicitudMiPagoCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof EstadosGeneral ))
            return false;

        EstadosGeneral other = ( EstadosGeneral ) object;

        if (( this.idEstado == null && other.idEstado != null ) || ( this.idEstado != null && !this.idEstado.equals( other.idEstado )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.EstadosGeneral[ idEstado=" + idEstado + " ]";
    }
}