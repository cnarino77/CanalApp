package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table( name = "datos_comercio" )
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosComercio.findAll", query = "SELECT d FROM DatosComercio d"),
    @NamedQuery(name = "DatosComercio.findByNitComercio", query = "SELECT d FROM DatosComercio d WHERE d.datosComercioPK.nitComercio = :nitComercio"),
    @NamedQuery(name = "DatosComercio.findByCodigoUnico", query = "SELECT d FROM DatosComercio d WHERE d.datosComercioPK.codigoUnico = :codigoUnico"),
    @NamedQuery(name = "DatosComercio.findByRazonSocial", query = "SELECT d FROM DatosComercio d WHERE d.razonSocial = :razonSocial"),
    @NamedQuery(name = "DatosComercio.findByNombreComercio", query = "SELECT d FROM DatosComercio d WHERE d.nombreComercio = :nombreComercio")})
public class DatosComercio implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DatosComercioPK datosComercioPK;

    @Column(name = "Razon_Social")
    private String razonSocial;

    @Column(name = "Nombre_Comercio")
    private String nombreComercio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datosComercio")
    private Collection<ContactoComercio> contactoComercioCollection;

    @JoinColumn(name = "Ciudad", referencedColumnName = "id_Ciudad")
    @ManyToOne(optional = false)
    private Ciudades ciudad;

    @JoinColumn(name = "MCC", referencedColumnName = "codigo_MCC")
    @ManyToOne(optional = false)
    private Mcc mcc;

    @Column(name = "Direccion")
    private String direccion;

    public DatosComercio() {
    }

    /**
     * @param datosComercioPK
     * @param razonSocial
     * @param nombreComercio
     * @param ciudad
     * @param mcc
     * @param direccion 
     */
    public DatosComercio( DatosComercioPK datosComercioPK, String razonSocial, String nombreComercio, Ciudades ciudad, Mcc mcc, String direccion ) {
        this.datosComercioPK = datosComercioPK;
        this.razonSocial = razonSocial;
        this.nombreComercio = nombreComercio;
        this.ciudad = ciudad;
        this.mcc = mcc;
        this.direccion = direccion;
    }

    /**
     * @param datosComercioPK 
     */
    public DatosComercio( DatosComercioPK datosComercioPK ) {
        this.datosComercioPK = datosComercioPK;
    }

    /**
     * @param nitComercio
     * @param codigoUnico 
     */
    public DatosComercio( String nitComercio, String codigoUnico ) {
        this.datosComercioPK = new DatosComercioPK(nitComercio, codigoUnico);
    }

    /**
     * @return DatosComercioPK
     */
    public DatosComercioPK getDatosComercioPK() {
        return datosComercioPK;
    }

    /**
     * @param datosComercioPK 
     */
    public void setDatosComercioPK( DatosComercioPK datosComercioPK ) {
        this.datosComercioPK = datosComercioPK;
    }

    /**
     * @return String
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial 
     */
    public void setRazonSocial( String razonSocial ) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return String
     */
    public String getNombreComercio() {
        return nombreComercio;
    }

    /**
     * @param nombreComercio 
     */
    public void setNombreComercio( String nombreComercio ) {
        this.nombreComercio = nombreComercio;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<ContactoComercio> getContactoComercioCollection() {
        return contactoComercioCollection;
    }

    /**
     * @param contactoComercioCollection 
     */
    public void setContactoComercioCollection( Collection<ContactoComercio> contactoComercioCollection ) {
        this.contactoComercioCollection = contactoComercioCollection;
    }

    /**
     * @return Ciudades
     */
    public Ciudades getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad 
     */
    public void setCiudad( Ciudades ciudad ) {
        this.ciudad = ciudad;
    }

    /**
     * @return Mcc
     */
    public Mcc getMcc() {
        return mcc;
    }

    /**
     * @param mcc 
     */
    public void setMcc( Mcc mcc ) {
        this.mcc = mcc;
    }

    /**
     * @return String
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion 
     */
    public void setDireccion( String direccion ) {
        this.direccion = direccion;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( datosComercioPK != null ? datosComercioPK.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof DatosComercio ))
            return false;

        DatosComercio other = ( DatosComercio ) object;

        if (( this.datosComercioPK == null && other.datosComercioPK != null ) || ( this.datosComercioPK != null && !this.datosComercioPK.equals( other.datosComercioPK )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.DatosComercio[ datosComercioPK=" + datosComercioPK + " ]";
    }
}