package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "Correos_Solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorreosSolicitudes.findAll", query = "SELECT c FROM CorreosSolicitudes c"),
    @NamedQuery(name = "CorreosSolicitudes.findByIdCorreo", query = "SELECT c FROM CorreosSolicitudes c WHERE c.idCorreo = :idCorreo"),
    @NamedQuery(name = "CorreosSolicitudes.findByUsuario", query = "SELECT c FROM CorreosSolicitudes c WHERE c.usuario = :usuario"),
    @NamedQuery(name = "CorreosSolicitudes.findByCorreo", query = "SELECT c FROM CorreosSolicitudes c WHERE c.correo = :correo"),
    @NamedQuery(name = "CorreosSolicitudes.findByOpc", query = "SELECT c FROM CorreosSolicitudes c WHERE c.opc = :opc")})
public class CorreosSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Correo")
    private Integer idCorreo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Usuario")
    private String usuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "correo")
    private String correo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "OPC")
    private String opc;

    @JoinColumn(name = "Id_Seccional", referencedColumnName = "Id_Seccional")
    @ManyToOne(optional = false)
    private SeccionalesCredibanco idSeccional;

    public CorreosSolicitudes() {
    }

    /**
     * @param idCorreo
     */
    public CorreosSolicitudes( Integer idCorreo ) {
        this.idCorreo = idCorreo;
    }

    /**
     * @param idCorreo
     * @param usuario
     * @param correo
     * @param opc
     */
    public CorreosSolicitudes( Integer idCorreo, String usuario, String correo, String opc ) {
        this.idCorreo = idCorreo;
        this.usuario = usuario;
        this.correo = correo;
        this.opc = opc;
    }

    /**
     * @return Integer
     */
    public Integer getIdCorreo() {
        return idCorreo;
    }

    /**
     * @param idCorreo
     */
    public void setIdCorreo( Integer idCorreo ) {
        this.idCorreo = idCorreo;
    }

    /**
     * @return String
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     */
    public void setUsuario( String usuario ) {
        this.usuario = usuario;
    }

    /**
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo
     */
    public void setCorreo( String correo ) {
        this.correo = correo;
    }

    /**
     * @return String
     */
    public String getOpc() {
        return opc;
    }

    /**
     * @param opc
     */
    public void setOpc( String opc ) {
        this.opc = opc;
    }

    /**
     * @return SeccionalesCredibanco
     */
    public SeccionalesCredibanco getIdSeccional() {
        return idSeccional;
    }

    /**
     * @param idSeccional
     */
    public void setIdSeccional( SeccionalesCredibanco idSeccional ) {
        this.idSeccional = idSeccional;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idCorreo != null ? idCorreo.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof CorreosSolicitudes ))
            return false;

        CorreosSolicitudes other = ( CorreosSolicitudes ) object;

        if (( this.idCorreo == null && other.idCorreo != null ) || ( this.idCorreo != null && !this.idCorreo.equals( other.idCorreo )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.CorreosSolicitudes[ idCorreo=" + idCorreo + " ]";
    }
}