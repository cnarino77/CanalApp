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
@Table(name = "destino_POS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DestinoPOS.findAll", query = "SELECT d FROM DestinoPOS d"),
    @NamedQuery(name = "DestinoPOS.findByIdDestino", query = "SELECT d FROM DestinoPOS d WHERE d.idDestino = :idDestino"),
    @NamedQuery(name = "DestinoPOS.findByDestinoPos", query = "SELECT d FROM DestinoPOS d WHERE d.destinoPos = :destinoPos"),
    @NamedQuery(name = "DestinoPOS.findByVisible", query = "SELECT d FROM DestinoPOS d WHERE d.visible = :visible")})
public class DestinoPOS implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinoPOS")
    private Collection<SolicitudPos> solicitudPosCollection;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Destino")
    private Integer idDestino;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Destino_Pos")
    private String destinoPos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    public DestinoPOS() {
    }

    /**
     * @param idDestino
     */
    public DestinoPOS( Integer idDestino ) {
        this.idDestino = idDestino;
    }

    /**
     * @param idDestino
     * @param destinoPos
     * @param visible
     */
    public DestinoPOS( Integer idDestino, String destinoPos, boolean visible ) {
        this.idDestino = idDestino;
        this.destinoPos = destinoPos;
        this.visible = visible;
    }

    /**
     * @return Integer
     */
    public Integer getIdDestino() {
        return idDestino;
    }

    /**
     * @param idDestino
     */
    public void setIdDestino( Integer idDestino ) {
        this.idDestino = idDestino;
    }

    /**
     * @return String
     */
    public String getDestinoPos() {
        return destinoPos;
    }

    /**
     * @param destinoPos
     */
    public void setDestinoPos( String destinoPos ) {
        this.destinoPos = destinoPos;
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
    public Collection<SolicitudPos> getSolicitudPosCollection() {
        return solicitudPosCollection;
    }

    /**
     * @param solicitudPosCollection
     */
    public void setSolicitudPosCollection( Collection<SolicitudPos> solicitudPosCollection ) {
        this.solicitudPosCollection = solicitudPosCollection;
    }   

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idDestino != null ? idDestino.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof DestinoPOS ))
            return false;

        DestinoPOS other = ( DestinoPOS ) object;
        if (( this.idDestino == null && other.idDestino != null ) || ( this.idDestino != null && !this.idDestino.equals( other.idDestino )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.DestinoPOS[ idDestino=" + idDestino + " ]";
    }
}