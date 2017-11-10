package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Basic;
import javax.persistence.Table;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Daniel Moreno
 * @version 1.0
 */
@Entity
@Table(name = "convenciones_Direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConvencionesDireccion.findAll", query = "SELECT c FROM ConvencionesDireccion c"),
    @NamedQuery(name = "ConvencionesDireccion.findByIdConvencion", query = "SELECT c FROM ConvencionesDireccion c WHERE c.idConvencion = :idConvencion"),
    @NamedQuery(name = "ConvencionesDireccion.findByDescripcion", query = "SELECT c FROM ConvencionesDireccion c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ConvencionesDireccion.findByIdCiudad", query = "SELECT c FROM ConvencionesDireccion c WHERE c.idCiudad = :idCiudad")})
public class ConvencionesDireccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Convencion")
    private String idConvencion;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Id_Ciudad")
    private String idCiudad;

    public ConvencionesDireccion() {
    }

    /**
     * @param idConvencion
     */
    public ConvencionesDireccion( String idConvencion ) {
        this.idConvencion = idConvencion;
    }

    /**
     * @return String
     */
    public String getIdConvencion() {
        return idConvencion;
    }

    /**
     * @param idConvencion
     */
    public void setIdConvencion( String idConvencion ) {
        this.idConvencion = idConvencion;
    }

    /**
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     */
    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    /**
     * @return String
     */
    public String getIdCiudad() {
        return idCiudad;
    }

    /**
     * @param idCiudad
     */
    public void setIdCiudad( String idCiudad ) {
        this.idCiudad = idCiudad;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idConvencion != null ? idConvencion.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof ConvencionesDireccion )) {
            return false;
        }
        ConvencionesDireccion other = ( ConvencionesDireccion ) object;
        if (( this.idConvencion == null && other.idConvencion != null ) || ( this.idConvencion != null && !this.idConvencion.equals( other.idConvencion ))) {
            return false;
        }
        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.ConvencionesDireccion[ idConvencion=" + idConvencion + " ]";
    }
}