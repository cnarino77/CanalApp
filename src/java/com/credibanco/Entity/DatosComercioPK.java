package com.credibanco.Entity;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Basic;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Embeddable
public class DatosComercioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nit_Comercio")
    private String nitComercio;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Codigo_Unico")
    private String codigoUnico;

    public DatosComercioPK() {
    }

    /**
     * @param nitComercio
     * @param codigoUnico
     */
    public DatosComercioPK( String nitComercio, String codigoUnico ) {
        this.nitComercio = nitComercio;
        this.codigoUnico = codigoUnico;
    }

    /**
     * @return String
     */
    public String getNitComercio() {
        return nitComercio;
    }

    /**
     * @param nitComercio
     */
    public void setNitComercio( String nitComercio ) {
        this.nitComercio = nitComercio;
    }

    /**
     * @return String
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }

    /**
     * @param codigoUnico
     */
    public void setCodigoUnico( String codigoUnico ) {
        this.codigoUnico = codigoUnico;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( nitComercio != null ? nitComercio.hashCode() : 0 );
        hash += ( codigoUnico != null ? codigoUnico.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof DatosComercioPK ))
            return false;

        DatosComercioPK other = ( DatosComercioPK ) object;

        if (( this.nitComercio == null && other.nitComercio != null ) || ( this.nitComercio != null && !this.nitComercio.equals( other.nitComercio )))
            return false;

        if (( this.codigoUnico == null && other.codigoUnico != null ) || ( this.codigoUnico != null && !this.codigoUnico.equals( other.codigoUnico )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.DatosComercioPK[ nitComercio=" + nitComercio + ", codigoUnico=" + codigoUnico + " ]";
    }
}