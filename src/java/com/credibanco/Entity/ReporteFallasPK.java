package com.credibanco.Entity;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Basic;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Embeddable
public class ReporteFallasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Solicitud")
    private String idSolicitud;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Usuario")
    private int idUsuario;

    public ReporteFallasPK() {
    }

    /**
     * @param idSolicitud
     * @param idUsuario
     */
    public ReporteFallasPK( String idSolicitud, int idUsuario ) {
        this.idSolicitud = idSolicitud;
        this.idUsuario = idUsuario;
    }

    /**
     * @return String
     */
    public String getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud
     */
    public void setIdSolicitud( String idSolicitud ) {
        this.idSolicitud = idSolicitud;
    }

    /**
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario
     */
    public void setIdUsuario( int idUsuario ) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idSolicitud != null ? idSolicitud.hashCode() : 0 );
        hash += ( int ) idUsuario;
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteFallasPK))
            return false;

        ReporteFallasPK other = ( ReporteFallasPK ) object;

        if (( this.idSolicitud == null && other.idSolicitud != null ) || ( this.idSolicitud != null && !this.idSolicitud.equals( other.idSolicitud )))
            return false;

        if (this.idUsuario != other.idUsuario)
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.ReporteFallasPK[ idSolicitud=" + idSolicitud + ", idUsuario=" + idUsuario + " ]";
    }
}