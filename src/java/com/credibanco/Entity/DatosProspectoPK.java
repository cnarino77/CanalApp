package com.credibanco.Entity;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Basic;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Daniel Moreno
 * @Version 1.1
 */
@Embeddable
public class DatosProspectoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Id")
    private int tipoId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Num_Id")
    private String numId;

    public DatosProspectoPK() {
    }

    /**
     * @param tipoId
     * @param numId
     */
    public DatosProspectoPK( int tipoId, String numId ) {
        this.tipoId = tipoId;
        this.numId = numId;
    }

    /**
     * @return int
     */
    public int getTipoId() {
        return tipoId;
    }

    /**
     * @param tipoId
     */
    public void setTipoId( int tipoId ) {
        this.tipoId = tipoId;
    }

    /**
     * @return String
     */
    public String getNumId() {
        return numId;
    }

    /**
     * @param numId
     */
    public void setNumId( String numId ) {
        this.numId = numId;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( tipoId );
        hash += ( numId != null ? numId.hashCode() : 0 );
        return hash;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.DatosProspectosPK[ tipoId=" + tipoId + ", numId=" + numId + " ]";
    }   
}