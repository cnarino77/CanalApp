package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
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
@Table(name = "seccionales_credibanco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeccionalesCredibanco.findAll", query = "SELECT s FROM SeccionalesCredibanco s"),
    @NamedQuery(name = "SeccionalesCredibanco.findByIdSeccional", query = "SELECT s FROM SeccionalesCredibanco s WHERE s.idSeccional = :idSeccional"),
    @NamedQuery(name = "SeccionalesCredibanco.findByNombreSeccional", query = "SELECT s FROM SeccionalesCredibanco s WHERE s.nombreSeccional = :nombreSeccional")})
public class SeccionalesCredibanco implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccional")
    private Collection<CorreosSolicitudes> correosSolicitudesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccional")
    private Collection<UsuariosInternos> usuariosInternosCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Seccional")
    private Integer idSeccional;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Seccional")
    private String nombreSeccional;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seccional")
    private Collection<Ciudades> ciudadesCollection;

    @JoinColumn(name = "Id_Regional", referencedColumnName = "Id_Regional")
    @ManyToOne(optional = false)
    private RegionalesCredibanco idRegional;

    public SeccionalesCredibanco() {
    }

    /**
     * @param idSeccional
     */
    public SeccionalesCredibanco( Integer idSeccional ) {
        this.idSeccional = idSeccional;
    }

    /**
     * @param idSeccional
     * @param nombreSeccional
     */
    public SeccionalesCredibanco( Integer idSeccional, String nombreSeccional ) {
        this.idSeccional = idSeccional;
        this.nombreSeccional = nombreSeccional;
    }

    /**
     * @return Integer
     */
    public Integer getIdSeccional() {
        return idSeccional;
    }

    /**
     * @param idSeccional
     */
    public void setIdSeccional( Integer idSeccional ) {
        this.idSeccional = idSeccional;
    }

    /**
     * @return String
     */
    public String getNombreSeccional() {
        return nombreSeccional;
    }

    /**
     * @param nombreSeccional
     */
    public void setNombreSeccional( String nombreSeccional ) {
        this.nombreSeccional = nombreSeccional;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<Ciudades> getCiudadesCollection() {
        return ciudadesCollection;
    }

    /**
     * @param ciudadesCollection
     */
    public void setCiudadesCollection( Collection<Ciudades> ciudadesCollection ) {
        this.ciudadesCollection = ciudadesCollection;
    }

    /**
     * @return RegionalesCredibanco
     */
    public RegionalesCredibanco getIdRegional() {
        return idRegional;
    }

    /**
     * @param idRegional
     */
    public void setIdRegional( RegionalesCredibanco idRegional ) {
        this.idRegional = idRegional;
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
    public Collection<UsuariosInternos> getUsuariosInternosCollection() {
        return usuariosInternosCollection;
    }

    /**
     * @param usuariosInternosCollection
     */
    public void setUsuariosInternosCollection( Collection<UsuariosInternos> usuariosInternosCollection ) {
        this.usuariosInternosCollection = usuariosInternosCollection;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<CorreosSolicitudes> getCorreosSolicitudesCollection() {
        return correosSolicitudesCollection;
    }

    /**
     * @param correosSolicitudesCollection
     */
    public void setCorreosSolicitudesCollection( Collection<CorreosSolicitudes> correosSolicitudesCollection ) {
        this.correosSolicitudesCollection = correosSolicitudesCollection;
    }
    
    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idSeccional != null ? idSeccional.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof SeccionalesCredibanco ))
            return false;

        SeccionalesCredibanco other = ( SeccionalesCredibanco ) object;

        if (( this.idSeccional == null && other.idSeccional != null ) || ( this.idSeccional != null && !this.idSeccional.equals( other.idSeccional )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.SeccionalesCredibanco[ idSeccional=" + idSeccional + " ]";
    }   
}