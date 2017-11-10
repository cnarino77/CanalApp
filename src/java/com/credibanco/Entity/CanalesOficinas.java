package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "canales_oficinas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanalesOficinas.findAll", query = "SELECT c FROM CanalesOficinas c"),
    @NamedQuery(name = "CanalesOficinas.findByIdCanal", query = "SELECT c FROM CanalesOficinas c WHERE c.canalesOficinasPK.idCanal = :idCanal"),
    @NamedQuery(name = "CanalesOficinas.findByIdOficina", query = "SELECT c FROM CanalesOficinas c WHERE c.canalesOficinasPK.idOficina = :idOficina"),
    @NamedQuery(name = "CanalesOficinas.findByTelefonoInformacion", query = "SELECT c FROM CanalesOficinas c WHERE c.telefonoInformacion = :telefonoInformacion"),
    @NamedQuery(name = "CanalesOficinas.findByTelefonoPOS", query = "SELECT c FROM CanalesOficinas c WHERE c.telefonoPOS = :telefonoPOS"),
    @NamedQuery(name = "CanalesOficinas.findByNombreOficina", query = "SELECT c FROM CanalesOficinas c WHERE c.nombreOficina = :nombreOficina"),
    @NamedQuery(name = "CanalesOficinas.findByCorreoOficina", query = "SELECT c FROM CanalesOficinas c WHERE c.correoOficina = :correoOficina")})
public class CanalesOficinas implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CanalesOficinasPK canalesOficinasPK;

    @Column(name = "Telefono_Informacion")
    private String telefonoInformacion;

    @Column(name = "Telefono_POS")
    private String telefonoPOS;

    @Column(name = "Nombre_Oficina")
    private String nombreOficina;

    @Column(name = "Correo_Oficina")
    private String correoOficina;

    @JoinColumn(name = "Id_Oficina", referencedColumnName = "Id_Oficina", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OficinasSeccionales oficinasSeccionales;

    public CanalesOficinas() {
    }

    /**
     * @param canalesOficinasPK 
     */
    public CanalesOficinas( CanalesOficinasPK canalesOficinasPK ) {
        this.canalesOficinasPK = canalesOficinasPK;
    }

    /**
     * @param idCanal
     * @param idOficina
     */
    public CanalesOficinas( int idCanal, int idOficina ) {
        this.canalesOficinasPK = new CanalesOficinasPK( idCanal, idOficina );
    }

    /**
     * @return CanalesOficinasPK
     */
    public CanalesOficinasPK getCanalesOficinasPK() {
        return canalesOficinasPK;
    }

    /**
     * @param canalesOficinasPK 
     */
    public void setCanalesOficinasPK( CanalesOficinasPK canalesOficinasPK ) {
        this.canalesOficinasPK = canalesOficinasPK;
    }

    /**
     * @return String
     */
    public String getTelefonoInformacion() {
        return telefonoInformacion;
    }

    /**
     * @param telefonoInformacion 
     */
    public void setTelefonoInformacion( String telefonoInformacion ) {
        this.telefonoInformacion = telefonoInformacion;
    }

    /**
     * @return String
     */
    public String getTelefonoPOS() {
        return telefonoPOS;
    }

    /**
     * @param telefonoPOS
     */
    public void setTelefonoPOS( String telefonoPOS ) {
        this.telefonoPOS = telefonoPOS;
    }

    /**
     * @return String
     */
    public String getNombreOficina() {
        return nombreOficina;
    }

    /**
     * @param nombreOficina
     */
    public void setNombreOficina( String nombreOficina ) {
        this.nombreOficina = nombreOficina;
    }

    /**
     * @return String
     */
    public String getCorreoOficina() {
        return correoOficina;
    }

    /**
     * @param correoOficina
     */
    public void setCorreoOficina( String correoOficina ) {
        this.correoOficina = correoOficina;
    }

    /**
     * @return OficinasSeccionales
     */
    public OficinasSeccionales getOficinasSeccionales() {
        return oficinasSeccionales;
    }

    /**
     * @param oficinasSeccionales
     */
    public void setOficinasSeccionales( OficinasSeccionales oficinasSeccionales ) {
        this.oficinasSeccionales = oficinasSeccionales;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( canalesOficinasPK != null ? canalesOficinasPK.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof CanalesOficinas ))
            return false;

        CanalesOficinas other = ( CanalesOficinas ) object;

        if (( this.canalesOficinasPK == null && other.canalesOficinasPK != null ) || ( this.canalesOficinasPK != null && !this.canalesOficinasPK.equals( other.canalesOficinasPK )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.CanalesOficinas[ canalesOficinasPK=" + canalesOficinasPK + " ]";
    }
}