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
@Table(name = "categorias_capacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasCapacitacion.findAll", query = "SELECT c FROM CategoriasCapacitacion c"),
    @NamedQuery(name = "CategoriasCapacitacion.findByIdCategoria", query = "SELECT c FROM CategoriasCapacitacion c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "CategoriasCapacitacion.findByCategoria", query = "SELECT c FROM CategoriasCapacitacion c WHERE c.categoria = :categoria"),
    @NamedQuery(name = "CategoriasCapacitacion.findByVisible", query = "SELECT c FROM CategoriasCapacitacion c WHERE c.visible = :visible"),
    @NamedQuery(name = "CategoriasCapacitacion.findByVisitas", query = "SELECT c FROM CategoriasCapacitacion c WHERE c.visitas = :visitas")})
public class CategoriasCapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Categoria")
    private Integer idCategoria;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Categoria")
    private String categoria;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visitas")
    private int visitas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaCap")
    private Collection<CapacitacionComercios> capacitacionComerciosCollection;

    public CategoriasCapacitacion() {
    }

    /**
     * @param idCategoria
     */
    public CategoriasCapacitacion( Integer idCategoria ) {
        this.idCategoria = idCategoria;
    }

    /**
     * @param idCategoria
     * @param categoria
     * @param visible
     * @param visitas
     */
    public CategoriasCapacitacion( Integer idCategoria, String categoria, boolean visible, int visitas ) {
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.visible = visible;
        this.visitas = visitas;
    }

    /**
     * @return Integer
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria
     */
    public void setIdCategoria( Integer idCategoria ) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return String
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria
     */
    public void setCategoria( String categoria ) {
        this.categoria = categoria;
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
     * @return int
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * @param visitas
     */
    public void setVisitas( int visitas ) {
        this.visitas = visitas;
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
        hash += ( idCategoria != null ? idCategoria.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof CategoriasCapacitacion ))
            return false;

        CategoriasCapacitacion other = ( CategoriasCapacitacion ) object;
        if (( this.idCategoria == null && other.idCategoria != null ) || ( this.idCategoria != null && !this.idCategoria.equals( other.idCategoria )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.CategoriasCapacitacion[ idCategoria=" + idCategoria + " ]";
    }
}