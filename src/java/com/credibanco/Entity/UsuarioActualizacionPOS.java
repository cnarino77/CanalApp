/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credibanco.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Daniel
 * @version 1.0
 */
@Entity
@Table(name = "usuarioActualizacionPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioActualizacionPOS.findAll", query = "SELECT u FROM UsuarioActualizacionPOS u"),
    @NamedQuery(name = "UsuarioActualizacionPOS.findByCodigoUnico", query = "SELECT u FROM UsuarioActualizacionPOS u WHERE u.codigoUnico = :codigoUnico"),
    @NamedQuery(name = "UsuarioActualizacionPOS.findByPassUsuario", query = "SELECT u FROM UsuarioActualizacionPOS u WHERE u.passUsuario = :passUsuario"),
    @NamedQuery(name = "UsuarioActualizacionPOS.findByLastAccess", query = "SELECT u FROM UsuarioActualizacionPOS u WHERE u.lastAccess = :lastAccess"),
    @NamedQuery(name = "UsuarioActualizacionPOS.findByLastDownload", query = "SELECT u FROM UsuarioActualizacionPOS u WHERE u.lastDownload = :lastDownload"),
    @NamedQuery(name = "UsuarioActualizacionPOS.findByLastVersion", query = "SELECT u FROM UsuarioActualizacionPOS u WHERE u.lastVersion = :lastVersion")})
public class UsuarioActualizacionPOS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_Unico")
    private String codigoUnico;

    @Basic(optional = false)
    @NotNull
    @Column(name = "pass_Usuario")
    private String passUsuario;

    @Column(name = "last_Access")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;

    @Column(name = "last_Download")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDownload;

    @Column(name = "last_Version")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastVersion;

    public UsuarioActualizacionPOS() {
    }

    public UsuarioActualizacionPOS(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public UsuarioActualizacionPOS(String codigoUnico, String passUsuario) {
        this.codigoUnico = codigoUnico;
        this.passUsuario = passUsuario;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Date getLastDownload() {
        return lastDownload;
    }

    public void setLastDownload(Date lastDownload) {
        this.lastDownload = lastDownload;
    }

    public Date getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(Date lastVersion) {
        this.lastVersion = lastVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUnico != null ? codigoUnico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioActualizacionPOS)) {
            return false;
        }
        UsuarioActualizacionPOS other = (UsuarioActualizacionPOS) object;
        if ((this.codigoUnico == null && other.codigoUnico != null) || (this.codigoUnico != null && !this.codigoUnico.equals(other.codigoUnico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.UsuarioActualizacionPOS[ codigoUnico=" + codigoUnico + " ]";
    }
}