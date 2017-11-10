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
@Table(name = "Departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByIdDepartamento", query = "SELECT d FROM Departamentos d WHERE d.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "Departamentos.findByNombreDepartamento", query = "SELECT d FROM Departamentos d WHERE d.nombreDepartamento = :nombreDepartamento")})
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_Departamento")
    private Integer idDepartamento;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Departamento")
    private String nombreDepartamento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private Collection<Ciudades> ciudadesCollection;

    public Departamentos() {
    }

    /**
     * @param idDepartamento
     */
    public Departamentos( Integer idDepartamento ) {
        this.idDepartamento = idDepartamento;
    }

    /**
     * @param idDepartamento
     * @param nombreDepartamento
     */
    public Departamentos( Integer idDepartamento, String nombreDepartamento ) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    /**
     * @return Integer
     */
    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    /**
     * @param idDepartamento
     */
    public void setIdDepartamento( Integer idDepartamento ) {
        this.idDepartamento = idDepartamento;
    }

    /**
     * @return String
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * @param nombreDepartamento
     */
    public void setNombreDepartamento( String nombreDepartamento ) {
        this.nombreDepartamento = nombreDepartamento;
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
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idDepartamento != null ? idDepartamento.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Departamentos ))
            return false;

        Departamentos other = ( Departamentos ) object;

        if (( this.idDepartamento == null && other.idDepartamento != null ) || ( this.idDepartamento != null && !this.idDepartamento.equals( other.idDepartamento )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.Departamentos[ idDepartamento=" + idDepartamento + " ]";
    }
}