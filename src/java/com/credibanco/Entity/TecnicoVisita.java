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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sami
 */
@Entity
@Table(name = "tecnico_visita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TecnicoVisita.findAll", query = "SELECT t FROM TecnicoVisita t"),
    @NamedQuery(name = "TecnicoVisita.findById", query = "SELECT t FROM TecnicoVisita t WHERE t.id = :id"),
    @NamedQuery(name = "TecnicoVisita.findByIdTecnico", query = "SELECT t FROM TecnicoVisita t WHERE t.idTecnico = :idTecnico"),
    @NamedQuery(name = "TecnicoVisita.findByNombreTecnico", query = "SELECT t FROM TecnicoVisita t WHERE t.nombreTecnico = :nombreTecnico"),
    @NamedQuery(name = "TecnicoVisita.findByApellidoTecnico", query = "SELECT t FROM TecnicoVisita t WHERE t.apellidoTecnico = :apellidoTecnico"),
    @NamedQuery(name = "TecnicoVisita.findByTelefonoTecnico", query = "SELECT t FROM TecnicoVisita t WHERE t.telefonoTecnico = :telefonoTecnico"),
    @NamedQuery(name = "TecnicoVisita.findByFechaCreacion", query = "SELECT t FROM TecnicoVisita t WHERE t.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "TecnicoVisita.findByFechaModificacion", query = "SELECT t FROM TecnicoVisita t WHERE t.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "TecnicoVisita.findByBorrado", query = "SELECT t FROM TecnicoVisita t WHERE t.borrado = :borrado")})
public class TecnicoVisita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tecnico")
    private String idTecnico;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "Foto_Tecnico")
    private byte[] fotoTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Tecnico")
    private String nombreTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Apellido_Tecnico")
    private String apellidoTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Telefono_Tecnico")
    private String telefonoTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "Fecha_Modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Borrado")
    private Boolean borrado;
    @JoinColumn(name = "Estado_Tecnico", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false)
    private EstadoTecnico estadoTecnico;
    
    @JoinColumn(name = "Tipo_Id_Tecnico", referencedColumnName = "id_Tipo")
    @ManyToOne(optional = false)
    private TipodocId tipoIdTecnico;

    public TecnicoVisita() {
    }

    public TecnicoVisita(Integer id) {
        this.id = id;
    }

    public TecnicoVisita(Integer id, String idTecnico, byte[] fotoTecnico, String nombreTecnico, String apellidoTecnico, String telefonoTecnico, Date fechaCreacion) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.fotoTecnico = fotoTecnico;
        this.nombreTecnico = nombreTecnico;
        this.apellidoTecnico = apellidoTecnico;
        this.telefonoTecnico = telefonoTecnico;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public byte[] getFotoTecnico() {
        return fotoTecnico;
    }

    public void setFotoTecnico(byte[] fotoTecnico) {
        this.fotoTecnico = fotoTecnico;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public String getApellidoTecnico() {
        return apellidoTecnico;
    }

    public void setApellidoTecnico(String apellidoTecnico) {
        this.apellidoTecnico = apellidoTecnico;
    }

    public String getTelefonoTecnico() {
        return telefonoTecnico;
    }

    public void setTelefonoTecnico(String telefonoTecnico) {
        this.telefonoTecnico = telefonoTecnico;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public EstadoTecnico getEstadoTecnico() {
        return estadoTecnico;
    }

    public void setEstadoTecnico(EstadoTecnico estadoTecnico) {
        this.estadoTecnico = estadoTecnico;
    }

    public TipodocId getTipoIdTecnico() {
        return tipoIdTecnico;
    }

    public void setTipoIdTecnico(TipodocId tipoIdTecnico) {
        this.tipoIdTecnico = tipoIdTecnico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TecnicoVisita)) {
            return false;
        }
        TecnicoVisita other = (TecnicoVisita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.TecnicoVisita[ id=" + id + " ]";
    }
    
}
