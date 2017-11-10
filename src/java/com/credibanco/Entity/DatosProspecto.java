package com.credibanco.Entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import java.util.Date;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "datos_prospecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosProspecto.findAll", query = "SELECT u FROM DatosProspecto u")})
public class DatosProspecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatosProspectoPK datosProspectoPK;
    
    @Column(name = "Razon_Social")
    private String razonSocial;
    
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_Ciudad")
    @ManyToOne(optional = false)
    private Ciudades ciudad;
    
    @Column(name = "Nombre_Contacto")
    private String nombreContacto;
    
    @Column(name = "ApellidoContacto")
    private String apellidoContacto;

    @Column(name = "Num_Movil")
    private String numMovil;

    @Column(name = "Num_Fijo")
    private String numFijo;

    @Column(name = "Correo_electronico")
    private String correoElectronico;

    @Column(name = "fecha_Registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    
    @Column(name = "Atendido")
    String atendido;

    @Column(name = "Fecha_Atendido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAtendido;
    
    @Column(name = "Direccion")
    private String direccion;

    public DatosProspecto() {
    }

    /**
     * @param datosProspectoPK
     * @param razonSocial
     * @param ciudad
     * @param nombreContacto
     * @param apellidoContacto
     * @param numMovil
     * @param numFijo
     * @param correoElectronico
     * @param fechaRegistro
     * @param atendido
     * @param direccion 
     */
    public DatosProspecto( DatosProspectoPK datosProspectoPK, String razonSocial, Ciudades ciudad, String nombreContacto, String apellidoContacto, String numMovil, String numFijo, String correoElectronico, Date fechaRegistro, String atendido, String direccion ) {
        this.datosProspectoPK = datosProspectoPK;
        this.razonSocial = razonSocial;
        this.ciudad = ciudad;
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.numMovil = numMovil;
        this.numFijo = numFijo;
        this.correoElectronico = correoElectronico;
        this.fechaRegistro = fechaRegistro;
        this.atendido = atendido;
        this.direccion = direccion;
    }

    /**
     * @param datosProspectoPK 
     */
    public DatosProspecto( DatosProspectoPK datosProspectoPK ) {
        this.datosProspectoPK = datosProspectoPK;
    }

    /**
     * @param tipoId
     * @param numId 
     */
    public DatosProspecto( int tipoId, String numId ) {
        this.datosProspectoPK = new DatosProspectoPK( tipoId, numId );
    }

    /**
     * @return DatosProspectoPK
     */
    public DatosProspectoPK getDatosProspectoPK() {
        return datosProspectoPK;
    }

    /**
     * @param datosProspectoPK
     */
    public void setDatosProspectoPK( DatosProspectoPK datosProspectoPK ) {
        this.datosProspectoPK = datosProspectoPK;
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
     * @return String
     */
    public String getNombreContacto() {
        return nombreContacto;
    }

    /**
     * @param nombreContacto 
     */
    public void setNombreContacto( String nombreContacto ) {
        this.nombreContacto = nombreContacto;
    }

    /**
     * @return String
     */
    public String getApellidoContacto() {
        return apellidoContacto;
    }

    /**
     * @param apellidoContacto
     */
    public void setApellidoContacto( String apellidoContacto ) {
        this.apellidoContacto = apellidoContacto;
    }

    /**
     * @return String
     */
    public String getNumMovil() {
        return numMovil;
    }

    /**
     * @param numMovil
     */
    public void setNumMovil( String numMovil ) {
        this.numMovil = numMovil;
    }

    /**
     * @return String
     */
    public String getNumFijo() {
        return numFijo;
    }

    /**
     * @param numFijo 
     */
    public void setNumFijo( String numFijo ) {
        this.numFijo = numFijo;
    }

    /**
     * @return String
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico
     */
    public void setCorreoElectronico( String correoElectronico ) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return Date
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro
     */
    public void setFechaRegistro( Date fechaRegistro ) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return String
     */
    public String getAtendido() {
        return atendido;
    }

    /**
     * @param atendido
     */
    public void setAtendido( String atendido ) {
        this.atendido = atendido;
    }

    /**
     * @return Date
     */
    public Date getFechaAtendido() {
        return fechaAtendido;
    }

    /**
     * @param fechaAtendido
     */
    public void setFechaAtendido( Date fechaAtendido ) {
        this.fechaAtendido = fechaAtendido;
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
        hash += ( datosProspectoPK != null ? datosProspectoPK.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof DatosProspecto ))
            return false;

        DatosProspecto other = ( DatosProspecto ) object;

        if (( this.datosProspectoPK == null && other.datosProspectoPK != null ) || ( this.datosProspectoPK != null && !this.datosProspectoPK.equals( other.datosProspectoPK )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.DatosProspecto[ datosProspectoPK=" + datosProspectoPK + " ]";
    }
}