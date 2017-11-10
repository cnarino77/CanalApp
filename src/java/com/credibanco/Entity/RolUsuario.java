package com.credibanco.Entity;

import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.CascadeType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
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
import java.util.Date;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "rol_Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolUsuario.findAll", query = "SELECT r FROM RolUsuario r"),
    @NamedQuery(name = "RolUsuario.findByIdRol", query = "SELECT r FROM RolUsuario r WHERE r.idRol = :idRol"),
    @NamedQuery(name = "RolUsuario.findByNombreRol", query = "SELECT r FROM RolUsuario r WHERE r.nombreRol = :nombreRol"),
    @NamedQuery(name = "RolUsuario.findByDescripcionRol", query = "SELECT r FROM RolUsuario r WHERE r.descripcionRol = :descripcionRol"),
    @NamedQuery(name = "RolUsuario.findByFechaCreacion", query = "SELECT r FROM RolUsuario r WHERE r.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "RolUsuario.findByActivo", query = "SELECT r FROM RolUsuario r WHERE r.activo = :activo"),
    @NamedQuery(name = "RolUsuario.findByInterno", query = "SELECT r FROM RolUsuario r WHERE r.interno = :interno")})
public class RolUsuario implements Serializable {

    @Column(name = "Admin")
    private Boolean admin;

    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private Collection<UsuariosInternos> usuariosInternosCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Rol")
    private Integer idRol;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Rol")
    private String nombreRol;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Descripcion_Rol")
    private String descripcionRol;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Activo")
    private boolean activo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Interno")
    private boolean interno;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private Collection<UsuariosComercios> usuariosComerciosCollection;

    public RolUsuario() {
    }

    /**
     * @param idRol
     */
    public RolUsuario( Integer idRol ) {
        this.idRol = idRol;
    }

    /**
     * @param idRol
     * @param nombreRol
     * @param descripcionRol
     * @param fechaCreacion
     * @param activo
     * @param interno 
     */
    public RolUsuario( Integer idRol, String nombreRol, String descripcionRol, Date fechaCreacion, boolean activo, boolean interno ) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
        this.interno = interno;
    }

    /**
     * @return Integer
     */
    public Integer getIdRol() {
        return idRol;
    }

    /**
     * @param idRol
     */
    public void setIdRol( Integer idRol ) {
        this.idRol = idRol;
    }

    /**
     * @return String
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * @param nombreRol
     */
    public void setNombreRol( String nombreRol ) {
        this.nombreRol = nombreRol;
    }

    /**
     * @return String
     */
    public String getDescripcionRol() {
        return descripcionRol;
    }

    /**
     * @param descripcionRol
     */
    public void setDescripcionRol( String descripcionRol ) {
        this.descripcionRol = descripcionRol;
    }

    /**
     * @return Date
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion
     */
    public void setFechaCreacion( Date fechaCreacion ) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return boolean
     */
    public boolean getActivo() {
        return activo;
    }

    /**
     * @param activo
     */
    public void setActivo( boolean activo ) {
        this.activo = activo;
    }

    /**
     * @return boolean
     */
    public boolean getInterno() {
        return interno;
    }

    /**
     * @param interno
     */
    public void setInterno( boolean interno ) {
        this.interno = interno;
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
    public Collection<UsuariosInternos> getUsuariosInternosCollection() {
        return usuariosInternosCollection;
    }

    /**
     * @param usuariosInternosCollection
     */
    public void setUsuariosInternosCollection( Collection<UsuariosInternos> usuariosInternosCollection ) {
        this.usuariosInternosCollection = usuariosInternosCollection;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<UsuariosComercios> getUsuariosComerciosCollection() {
        return usuariosComerciosCollection;
    }

    /**
     * @param usuariosComerciosCollection
     */
    public void setUsuariosComerciosCollection( Collection<UsuariosComercios> usuariosComerciosCollection ) {
        this.usuariosComerciosCollection = usuariosComerciosCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof RolUsuario ))
            return false;

        RolUsuario other = ( RolUsuario ) object;

        if (( this.idRol == null && other.idRol != null ) || ( this.idRol != null && !this.idRol.equals( other.idRol )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.RolUsuario[ idRol=" + idRol + " ]";
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}