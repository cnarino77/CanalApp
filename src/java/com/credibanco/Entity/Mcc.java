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
@Table(name = "MCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mcc.findAll", query = "SELECT m FROM Mcc m"),
    @NamedQuery(name = "Mcc.findByIdMCC", query = "SELECT m FROM Mcc m WHERE m.idMCC = :idMCC"),
    @NamedQuery(name = "Mcc.findByCodigoMCC", query = "SELECT m FROM Mcc m WHERE m.codigoMCC = :codigoMCC"),
    @NamedQuery(name = "Mcc.findByTipo", query = "SELECT m FROM Mcc m WHERE m.tipo = :tipo")})
public class Mcc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_MCC")
    private int idMCC;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_MCC")
    private String codigoMCC;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo")
    private String tipo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mcc")
    private Collection<DatosComercio> datosComercioCollection;

    public Mcc() {
    }

    /**
     * @param codigoMCC
     */
    public Mcc( String codigoMCC ) {
        this.codigoMCC = codigoMCC;
    }

    /**
     * @param codigoMCC
     * @param idMCC
     * @param tipo
     */
    public Mcc( String codigoMCC, int idMCC, String tipo ) {
        this.codigoMCC = codigoMCC;
        this.idMCC = idMCC;
        this.tipo = tipo;
    }

    /**
     * @return int
     */
    public int getIdMCC() {
        return idMCC;
    }

    /**
     * @param idMCC
     */
    public void setIdMCC( int idMCC ) {
        this.idMCC = idMCC;
    }

    /**
     * @return String
     */
    public String getCodigoMCC() {
        return codigoMCC;
    }

    /**
     * @param codigoMCC
     */
    public void setCodigoMCC( String codigoMCC ) {
        this.codigoMCC = codigoMCC;
    }

    /**
     * @return String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     */
    public void setTipo( String tipo ) {
        this.tipo = tipo;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<DatosComercio> getDatosComercioCollection() {
        return datosComercioCollection;
    }

    /**
     * @param datosComercioCollection
     */
    public void setDatosComercioCollection( Collection<DatosComercio> datosComercioCollection ) {
        this.datosComercioCollection = datosComercioCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( codigoMCC != null ? codigoMCC.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Mcc ))
            return false;

        Mcc other = ( Mcc ) object;

        if (( this.codigoMCC == null && other.codigoMCC != null ) || ( this.codigoMCC != null && !this.codigoMCC.equals( other.codigoMCC )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.Mcc[ codigoMCC=" + codigoMCC + " ]";
    }
}