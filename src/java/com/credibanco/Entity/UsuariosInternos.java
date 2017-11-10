package com.credibanco.Entity;

import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "usuarios_Internos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosInternos.findAll", query = "SELECT u FROM UsuariosInternos u"),
    @NamedQuery(name = "UsuariosInternos.findByIdUsuario", query = "SELECT u FROM UsuariosInternos u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuariosInternos.findByUsername", query = "SELECT u FROM UsuariosInternos u WHERE u.username = :username"),
    @NamedQuery(name = "UsuariosInternos.findByEstadoUsuario", query = "SELECT u FROM UsuariosInternos u WHERE u.estadoUsuario = :estadoUsuario"),
    @NamedQuery(name = "UsuariosInternos.findByNombreUsuario", query = "SELECT u FROM UsuariosInternos u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "UsuariosInternos.findByEMail", query = "SELECT u FROM UsuariosInternos u WHERE u.eMail = :eMail"),
    @NamedQuery(name = "UsuariosInternos.findByUltimoIngreso", query = "SELECT u FROM UsuariosInternos u WHERE u.ultimoIngreso = :ultimoIngreso")})
public class UsuariosInternos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Usuario")
    private int idUsuario;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Username")
    private String username;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado_Usuario")
    private boolean estadoUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Usuario")
    private String nombreUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "E_Mail")
    private String eMail;

    @Column(name = "Ultimo_Ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoIngreso;

    @JoinColumn(name = "Id_Rol", referencedColumnName = "Id_Rol")
    @ManyToOne(optional = false)
    private RolUsuario idRol;

    @JoinColumn(name = "Id_Seccional", referencedColumnName = "Id_Seccional")
    @ManyToOne(optional = false)
    private SeccionalesCredibanco idSeccional;

    public UsuariosInternos() {
    }

    public UsuariosInternos( String username ) {
        this.username = username;
    }

    /**
     * @param username
     * @param idUsuario
     * @param estadoUsuario
     * @param nombreUsuario
     * @param eMail 
     */
    public UsuariosInternos( String username, int idUsuario, boolean estadoUsuario, String nombreUsuario, String eMail ) {
        this.username = username;
        this.idUsuario = idUsuario;
        this.estadoUsuario = estadoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.eMail = eMail;
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
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername( String username ) {
        this.username = username;
    }

    /**
     * @return boolean
     */
    public boolean getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * @param estadoUsuario
     */
    public void setEstadoUsuario( boolean estadoUsuario ) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * @return String
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario
     */
    public void setNombreUsuario( String nombreUsuario ) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return String
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * @param eMail
     */
    public void setEMail( String eMail ) {
        this.eMail = eMail;
    }

    /**
     * @return Date
     */
    public Date getUltimoIngreso() {
        return ultimoIngreso;
    }

    /**
     * @param ultimoIngreso
     */
    public void setUltimoIngreso( Date ultimoIngreso ) {
        this.ultimoIngreso = ultimoIngreso;
    }

    /**
     * @return RolUsuario
     */
    public RolUsuario getIdRol() {
        return idRol;
    }

    /**
     * @param idRol
     */
    public void setIdRol( RolUsuario idRol ) {
        this.idRol = idRol;
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
        hash += ( username != null ? username.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof UsuariosInternos )) {
            return false;
        }
        UsuariosInternos other = ( UsuariosInternos ) object;
        if (( this.username == null && other.username != null ) || ( this.username != null && !this.username.equals( other.username ))) {
            return false;
        }
        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.UsuariosInternos[ username=" + username + " ]";
    }
}