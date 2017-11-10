package com.credibanco.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipo_doc_Id")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipodocId.findAll", query = "SELECT t FROM TipodocId t"),
    @NamedQuery(name = "TipodocId.findByIdTipo", query = "SELECT t FROM TipodocId t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipodocId.findByTipo", query = "SELECT t FROM TipodocId t WHERE t.tipo = :tipo")})
public class TipodocId implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIdTecnico")
    private Collection<TecnicoVisita> tecnicoVisitaCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id_Tipo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo")
    private String tipo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIdContacto")
    private Collection<ContactoComercio> contactoComercioCollection;

    public TipodocId() {
    }

    /**
     * @param idTipo 
     */
    public TipodocId( Integer idTipo ) {
        this.idTipo = idTipo;
    }

    /**
     * @param idTipo
     * @param tipo 
     */
    public TipodocId( Integer idTipo, String tipo ) {
        this.idTipo = idTipo;
        this.tipo = tipo;
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
        if (!( object instanceof TipodocId ))
            return false;
        TipodocId other = ( TipodocId ) object;

        if (( this.idTipo == null && other.idTipo != null ) || ( this.idTipo != null && !this.idTipo.equals( other.idTipo )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.TipodocId[ idTipo=" + idTipo + " ]";
    }
}