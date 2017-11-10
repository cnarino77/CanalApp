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
@Table(name = "oficinas_seccionales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OficinasSeccionales.findAll", query = "SELECT o FROM OficinasSeccionales o"),
    @NamedQuery(name = "OficinasSeccionales.findByIdOficina", query = "SELECT o FROM OficinasSeccionales o WHERE o.idOficina = :idOficina"),
    @NamedQuery(name = "OficinasSeccionales.findByDireccionOficina", query = "SELECT o FROM OficinasSeccionales o WHERE o.direccionOficina = :direccionOficina"),
    @NamedQuery(name = "OficinasSeccionales.findByVisible", query = "SELECT o FROM OficinasSeccionales o WHERE o.visible = :visible")})
public class OficinasSeccionales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Oficina")
    private Integer idOficina;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Direccion_Oficina")
    private String direccionOficina;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @JoinColumn(name = "Ciudad_Oficina", referencedColumnName = "id_Ciudad")
    @ManyToOne(optional = false)
    private Ciudades ciudadOficina;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oficinasSeccionales")
    private Collection<CanalesOficinas> canalesOficinasCollection;

    public OficinasSeccionales() {
    }

    /**
     * @param idOficina
     */
    public OficinasSeccionales( Integer idOficina ) {
        this.idOficina = idOficina;
    }

    /**
     * @param idOficina
     * @param direccionOficina
     * @param visible
     */
    public OficinasSeccionales( Integer idOficina, String direccionOficina, boolean visible ) {
        this.idOficina = idOficina;
        this.direccionOficina = direccionOficina;
        this.visible = visible;
    }

    /**
     * @return Integer
     */
    public Integer getIdOficina() {
        return idOficina;
    }

    /**
     * @param idOficina
     */
    public void setIdOficina( Integer idOficina ) {
        this.idOficina = idOficina;
    }

    /**
     * @return String
     */
    public String getDireccionOficina() {
        return direccionOficina;
    }

    /**
     * @param direccionOficina
     */
    public void setDireccionOficina( String direccionOficina ) {
        this.direccionOficina = direccionOficina;
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
     * @return Ciudades
     */
    public Ciudades getCiudadOficina() {
        return ciudadOficina;
    }

    /**
     * @param ciudadOficina
     */
    public void setCiudadOficina( Ciudades ciudadOficina ) {
        this.ciudadOficina = ciudadOficina;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<CanalesOficinas> getCanalesOficinasCollection() {
        return canalesOficinasCollection;
    }

    /**
     * @param canalesOficinasCollection
     */
    public void setCanalesOficinasCollection( Collection<CanalesOficinas> canalesOficinasCollection ) {
        this.canalesOficinasCollection = canalesOficinasCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idOficina != null ? idOficina.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof OficinasSeccionales ))
            return false;

        OficinasSeccionales other = ( OficinasSeccionales ) object;

        if (( this.idOficina == null && other.idOficina != null ) || ( this.idOficina != null && !this.idOficina.equals( other.idOficina )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.OficinasSeccionales[ idOficina=" + idOficina + " ]";
    }
}