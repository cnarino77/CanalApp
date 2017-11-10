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
@Table(name = "regionales_Credibanco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegionalesCredibanco.findAll", query = "SELECT r FROM RegionalesCredibanco r"),
    @NamedQuery(name = "RegionalesCredibanco.findByIdRegional", query = "SELECT r FROM RegionalesCredibanco r WHERE r.idRegional = :idRegional"),
    @NamedQuery(name = "RegionalesCredibanco.findByNombreRegional", query = "SELECT r FROM RegionalesCredibanco r WHERE r.nombreRegional = :nombreRegional"),
    @NamedQuery(name = "RegionalesCredibanco.findByCodigoRegional", query = "SELECT r FROM RegionalesCredibanco r WHERE r.codigoRegional = :codigoRegional")})
public class RegionalesCredibanco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Regional")
    private Integer idRegional;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Regional")
    private String nombreRegional;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Codigo_Regional")
    private String codigoRegional;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegional")
    private Collection<SeccionalesCredibanco> seccionalesCredibancoCollection;

    public RegionalesCredibanco() {
    }

    /**
     * @param idRegional 
     */
    public RegionalesCredibanco(Integer idRegional) {
        this.idRegional = idRegional;
    }

    /**
     * @param idRegional
     * @param nombreRegional
     * @param codigoRegional
     */
    public RegionalesCredibanco( Integer idRegional, String nombreRegional, String codigoRegional ) {
        this.idRegional = idRegional;
        this.nombreRegional = nombreRegional;
        this.codigoRegional = codigoRegional;
    }

    /**
     * @return Integer
     */
    public Integer getIdRegional() {
        return idRegional;
    }

    /**
     * @param idRegional
     */
    public void setIdRegional( Integer idRegional ) {
        this.idRegional = idRegional;
    }

    /**
     * @return String
     */
    public String getNombreRegional() {
        return nombreRegional;
    }

    /**
     * @param nombreRegional
     */
    public void setNombreRegional( String nombreRegional ) {
        this.nombreRegional = nombreRegional;
    }

    /**
     * @return String
     */
    public String getCodigoRegional() {
        return codigoRegional;
    }

    /**
     * @param codigoRegional
     */
    public void setCodigoRegional( String codigoRegional ) {
        this.codigoRegional = codigoRegional;
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
    public Collection<SeccionalesCredibanco> getSeccionalesCredibancoCollection() {
        return seccionalesCredibancoCollection;
    }

    /**
     * @param seccionalesCredibancoCollection
     */
    public void setSeccionalesCredibancoCollection( Collection<SeccionalesCredibanco> seccionalesCredibancoCollection ) {
        this.seccionalesCredibancoCollection = seccionalesCredibancoCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idRegional != null ? idRegional.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof RegionalesCredibanco ))
            return false;

        RegionalesCredibanco other = ( RegionalesCredibanco ) object;

        if (( this.idRegional == null && other.idRegional != null ) || ( this.idRegional != null && !this.idRegional.equals( other.idRegional )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.RegionalesCredibanco[ idRegional=" + idRegional + " ]";
    }
}