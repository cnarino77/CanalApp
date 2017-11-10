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
@Table(name = "tipo_Capacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCapacitacion.findAll", query = "SELECT t FROM TipoCapacitacion t"),
    @NamedQuery(name = "TipoCapacitacion.findByIdTipo", query = "SELECT t FROM TipoCapacitacion t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoCapacitacion.findByTipoCapacitacion", query = "SELECT t FROM TipoCapacitacion t WHERE t.tipoCapacitacion = :tipoCapacitacion")})
public class TipoCapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipo")
    private Integer idTipo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Capacitacion")
    private String tipoCapacitacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCapacitacion")
    private Collection<CapacitacionComercios> capacitacionComerciosCollection;

    public TipoCapacitacion() {
    }

    /**
     * @param idTipo 
     */
    public TipoCapacitacion( Integer idTipo ) {
        this.idTipo = idTipo;
    }

    /**
     * @param idTipo
     * @param tipoCapacitacion 
     */
    public TipoCapacitacion( Integer idTipo, String tipoCapacitacion ) {
        this.idTipo = idTipo;
        this.tipoCapacitacion = tipoCapacitacion;
    }

    /**
     * @return Integer
     */
    public Integer getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo
     */
    public void setIdTipo( Integer idTipo ) {
        this.idTipo = idTipo;
    }

    /**
     * @return String
     */
    public String getTipoCapacitacion() {
        return tipoCapacitacion;
    }

    /**
     * @param tipoCapacitacion
     */
    public void setTipoCapacitacion( String tipoCapacitacion ) {
        this.tipoCapacitacion = tipoCapacitacion;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<CapacitacionComercios> getCapacitacionComerciosCollection() {
        return capacitacionComerciosCollection;
    }

    /**
     * @param capacitacionComerciosCollection
     */
    public void setCapacitacionComerciosCollection( Collection<CapacitacionComercios> capacitacionComerciosCollection ) {
        this.capacitacionComerciosCollection = capacitacionComerciosCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idTipo != null ? idTipo.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof TipoCapacitacion ))
            return false;
        TipoCapacitacion other = ( TipoCapacitacion ) object;

        if (( this.idTipo == null && other.idTipo != null ) || ( this.idTipo != null && !this.idTipo.equals( other.idTipo )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.TipoCapacitacion[ idTipo=" + idTipo + " ]";
    }
}
