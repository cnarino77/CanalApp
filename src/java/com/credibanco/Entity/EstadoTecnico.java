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
@Table(name = "Estado_Tecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoTecnico.findAll", query = "SELECT e FROM EstadoTecnico e"),
    @NamedQuery(name = "EstadoTecnico.findByIdEstado", query = "SELECT e FROM EstadoTecnico e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "EstadoTecnico.findByEstadoTecnico", query = "SELECT e FROM EstadoTecnico e WHERE e.estadoTecnico = :estadoTecnico")})
public class EstadoTecnico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoTecnico")
    private Collection<TecnicoVisita> tecnicoVisitaCollection;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Estado")
    private Integer idEstado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado_Tecnico")
    private String estadoTecnico;

    public EstadoTecnico() {
    }

    /**
     * @param idEstado
     */
    public EstadoTecnico( Integer idEstado ) {
        this.idEstado = idEstado;
    }

    /**
     * @param idEstado
     * @param estadoTecnico
     */
    public EstadoTecnico( Integer idEstado, String estadoTecnico ) {
        this.idEstado = idEstado;
        this.estadoTecnico = estadoTecnico;
    }

    /**
     * @return Integer
     */
    public Integer getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado
     */
    public void setIdEstado( Integer idEstado ) {
        this.idEstado = idEstado;
    }

    /**
     * @return String
     */
    public String getEstadoTecnico() {
        return estadoTecnico;
    }

    /**
     * @param estadoTecnico
     */
    public void setEstadoTecnico( String estadoTecnico ) {
        this.estadoTecnico = estadoTecnico;
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
    public Collection<TecnicoVisita> getTecnicoVisitaCollection() {
        return tecnicoVisitaCollection;
    }

    /**
     * @param tecnicoVisitaCollection
     */
    public void setTecnicoVisitaCollection( Collection<TecnicoVisita> tecnicoVisitaCollection ) {
        this.tecnicoVisitaCollection = tecnicoVisitaCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idEstado != null ? idEstado.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof EstadoTecnico ))
            return false;

        EstadoTecnico other = ( EstadoTecnico ) object;

        if (( this.idEstado == null && other.idEstado != null ) || ( this.idEstado != null && !this.idEstado.equals( other.idEstado )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.EstadoTecnico[ idEstado=" + idEstado + " ]";
    }
}