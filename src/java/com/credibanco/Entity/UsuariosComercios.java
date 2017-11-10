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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sami
 */
@Entity
@Table(name = "usuarios_comercios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosComercios.findAll", query = "SELECT u FROM UsuariosComercios u"),
    @NamedQuery(name = "UsuariosComercios.findByIdUsuario", query = "SELECT u FROM UsuariosComercios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuariosComercios.findByPassUsuario", query = "SELECT u FROM UsuariosComercios u WHERE u.passUsuario = :passUsuario"),
    @NamedQuery(name = "UsuariosComercios.findByVersionTerminos", query = "SELECT u FROM UsuariosComercios u WHERE u.versionTerminos = :versionTerminos"),
    @NamedQuery(name = "UsuariosComercios.findByUltimoingreso", query = "SELECT u FROM UsuariosComercios u WHERE u.ultimoingreso = :ultimoingreso"),
    @NamedQuery(name = "UsuariosComercios.findByAccesoBloqueado", query = "SELECT u FROM UsuariosComercios u WHERE u.accesoBloqueado = :accesoBloqueado"),
    @NamedQuery(name = "UsuariosComercios.findByFechaRegistro", query = "SELECT u FROM UsuariosComercios u WHERE u.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "UsuariosComercios.findByFechaTerminos", query = "SELECT u FROM UsuariosComercios u WHERE u.fechaTerminos = :fechaTerminos"),
    @NamedQuery(name = "UsuariosComercios.findByPassTemporal", query = "SELECT u FROM UsuariosComercios u WHERE u.passTemporal = :passTemporal"),
    @NamedQuery(name = "UsuariosComercios.findByImei", query = "SELECT u FROM UsuariosComercios u WHERE u.imei = :imei"),
    @NamedQuery(name = "UsuariosComercios.findByToken", query = "SELECT u FROM UsuariosComercios u WHERE u.token = :token")})
public class UsuariosComercios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "Id_Usuario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Pass_Usuario")
    private String passUsuario;

    @Column(name = "Version_Terminos")
    private Integer versionTerminos;

    @Column(name = "Ultimo_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoingreso;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Acceso_Bloqueado")
    private boolean accesoBloqueado;
    
    @Column(name = "fecha_Registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    
    @Column(name = "Fecha_Terminos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTerminos;
    
    @Column(name = "Pass_Temporal")
    private String passTemporal;
    
    @Column(name = "Imei")
    private String imei;

    @Lob
    @Column(name = "Token")
    private String token;

    @JoinColumn(name = "Id_Contacto", referencedColumnName = "Id_Contacto")
    @ManyToOne(optional = false)
    private ContactoComercio idContacto;

    @JoinColumn(name = "Id_Rol", referencedColumnName = "Id_Rol")
    @ManyToOne(optional = false)
    private RolUsuario idRol;

    public UsuariosComercios() {
    }

    public UsuariosComercios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuariosComercios(Integer idUsuario, String passUsuario, boolean accesoBloqueado) {
        this.idUsuario = idUsuario;
        this.passUsuario = passUsuario;
        this.accesoBloqueado = accesoBloqueado;
    }
    
        /**
     * @param passUsuario
     * @param versionTerminos
     * @param ultimoingreso
     * @param fechaRegistro
     * @param fechaTerminos
     * @param imei
     * @param idContacto
     * @param idRol 
     */
    public UsuariosComercios( String passUsuario, Integer versionTerminos, Date ultimoingreso, Date fechaRegistro, Date fechaTerminos, String imei, ContactoComercio idContacto, RolUsuario idRol ) {
        this.passUsuario = passUsuario;
        this.versionTerminos = versionTerminos;
        this.ultimoingreso = ultimoingreso;
        this.fechaRegistro = fechaRegistro;
        this.fechaTerminos = fechaTerminos;
        this.imei = imei;
        this.idContacto = idContacto;
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public Integer getVersionTerminos() {
        return versionTerminos;
    }

    public void setVersionTerminos(Integer versionTerminos) {
        this.versionTerminos = versionTerminos;
    }

    public Date getUltimoingreso() {
        return ultimoingreso;
    }

    public void setUltimoingreso(Date ultimoingreso) {
        this.ultimoingreso = ultimoingreso;
    }

    public boolean getAccesoBloqueado() {
        return accesoBloqueado;
    }

    public void setAccesoBloqueado(boolean accesoBloqueado) {
        this.accesoBloqueado = accesoBloqueado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaTerminos() {
        return fechaTerminos;
    }

    public void setFechaTerminos(Date fechaTerminos) {
        this.fechaTerminos = fechaTerminos;
    }

    public String getPassTemporal() {
        return passTemporal;
    }

    public void setPassTemporal(String passTemporal) {
        this.passTemporal = passTemporal;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ContactoComercio getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(ContactoComercio idContacto) {
        this.idContacto = idContacto;
    }

    public RolUsuario getIdRol() {
        return idRol;
    }

    public void setIdRol(RolUsuario idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosComercios)) {
            return false;
        }
        UsuariosComercios other = (UsuariosComercios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.UsuariosComercios[ idUsuario=" + idUsuario + " ]";
    }
}
