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
@Table(name = "Ciudades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudades.findAll", query = "SELECT c FROM Ciudades c"),
    @NamedQuery(name = "Ciudades.findByIdCiudad", query = "SELECT c FROM Ciudades c WHERE c.idCiudad = :idCiudad"),
    @NamedQuery(name = "Ciudades.findByNombreCiudad", query = "SELECT c FROM Ciudades c WHERE c.nombreCiudad = :nombreCiudad")})
public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadOficina")
    private Collection<OficinasSeccionales> oficinasSeccionalesCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_Ciudad")
    private Integer idCiudad;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Ciudad")
    private String nombreCiudad;

    @JoinColumn(name = "Departamento", referencedColumnName = "id_Departamento")
    @ManyToOne(optional = false)
    private Departamentos departamento;

    @JoinColumn(name = "Seccional", referencedColumnName = "Id_Seccional")
    @ManyToOne(optional = false)
    private SeccionalesCredibanco seccional;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudad")
    private Collection<DatosComercio> datosComercioCollection;

    public Ciudades() {
    }

    /**
     * @param idCiudad
     */
    public Ciudades( Integer idCiudad ) {
        this.idCiudad = idCiudad;
    }

    /**
     * @param idCiudad
     * @param nombreCiudad
     */
    public Ciudades ( Integer idCiudad, String nombreCiudad ) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    /**
     * @return Integer
     */
    public Integer getIdCiudad() {
        return idCiudad;
    }

    /**
     * @param idCiudad
     */
    public void setIdCiudad( Integer idCiudad ) {
        this.idCiudad = idCiudad;
    }

    /**
     * @return String
     */
    public String getNombreCiudad() {
        return nombreCiudad;
    }

    /**
     * @param nombreCiudad
     */
    public void setNombreCiudad( String nombreCiudad ) {
        this.nombreCiudad = nombreCiudad;
    }

    /**
     * @return Departamentos
     */
    public Departamentos getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento
     */
    public void setDepartamento( Departamentos departamento ) {
        this.departamento = departamento;
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
    public Collection<OficinasSeccionales> getOficinasSeccionalesCollection() {
        return oficinasSeccionalesCollection;
    }

    /**
     * @param oficinasSeccionalesCollection
     */
    public void setOficinasSeccionalesCollection( Collection<OficinasSeccionales> oficinasSeccionalesCollection ) {
        this.oficinasSeccionalesCollection = oficinasSeccionalesCollection;
    }
    
    /**
     * @return SeccionalesCredibanco
     */
    public SeccionalesCredibanco getSeccional() {
        return seccional;
    }

    /**
     * @param seccional
     */
    public void setSeccional( SeccionalesCredibanco seccional ) {
        this.seccional = seccional;
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
        hash += ( idCiudad != null ? idCiudad.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Ciudades ))
            return false;

        Ciudades other = (Ciudades) object;

        if (( this.idCiudad == null && other.idCiudad != null ) || ( this.idCiudad != null && !this.idCiudad.equals( other.idCiudad )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.Ciudades[ idCiudad=" + idCiudad + " ]";
    }
}