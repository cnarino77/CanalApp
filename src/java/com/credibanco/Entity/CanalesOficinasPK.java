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
public class CanalesOficinasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Canal")
    private int idCanal;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Oficina")
    private int idOficina;

    public CanalesOficinasPK() {
    }

    /**
     * @param idCanal
     * @param idOficina
     */
    public CanalesOficinasPK( int idCanal, int idOficina ) {
        this.idCanal = idCanal;
        this.idOficina = idOficina;
    }

    /**
     * @return int
     */
    public int getIdCanal() {
        return idCanal;
    }

    /**
     * @param idCanal
     */
    public void setIdCanal( int idCanal ) {
        this.idCanal = idCanal;
    }

    /**
     * @return int
     */
    public int getIdOficina() {
        return idOficina;
    }

    /**
     * @param idOficina
     */
    public void setIdOficina( int idOficina ) {
        this.idOficina = idOficina;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( int ) idCanal;
        hash += ( int ) idOficina;
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof CanalesOficinasPK ))
            return false;

        CanalesOficinasPK other = ( CanalesOficinasPK ) object;

        if ( this.idCanal != other.idCanal )
            return false;

        if ( this.idOficina != other.idOficina )
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.CanalesOficinasPK[ idCanal=" + idCanal + ", idOficina=" + idOficina + " ]";
    }
}