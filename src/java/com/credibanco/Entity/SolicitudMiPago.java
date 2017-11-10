package com.credibanco.Entity;

import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;

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
@Table(name = "solicitud_MiPago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudMiPago.findAll", query = "SELECT s FROM SolicitudMiPago s"),
    @NamedQuery(name = "SolicitudMiPago.findByIdSolicitud", query = "SELECT s FROM SolicitudMiPago s WHERE s.solicitudMiPagoPK.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "SolicitudMiPago.findByIdUsuario", query = "SELECT s FROM SolicitudMiPago s WHERE s.solicitudMiPagoPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "SolicitudMiPago.findByFechaCreacion", query = "SELECT s FROM SolicitudMiPago s WHERE s.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "SolicitudMiPago.findByCantidadMiPago", query = "SELECT s FROM SolicitudMiPago s WHERE s.cantidadMiPago = :cantidadMiPago"),
    @NamedQuery(name = "SolicitudMiPago.findByFechaAtencion", query = "SELECT s FROM SolicitudMiPago s WHERE s.fechaAtencion = :fechaAtencion"),
    @NamedQuery(name = "SolicitudMiPago.findByRespuesta", query = "SELECT s FROM SolicitudMiPago s WHERE s.respuesta = :respuesta"),
    @NamedQuery(name = "SolicitudMiPago.findByFechaRespuesta", query = "SELECT s FROM SolicitudMiPago s WHERE s.fechaRespuesta = :fechaRespuesta")})
public class SolicitudMiPago implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected SolicitudMiPagoPK solicitudMiPagoPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad_MiPago")
    private int cantidadMiPago;

    @Column(name = "Fecha_Atencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAtencion;

    @Column(name = "Respuesta")
    private String respuesta;

    @Column(name = "Fecha_Respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;

    @JoinColumn(name = "Id_Estado", referencedColumnName = "id_Estado")
    @ManyToOne(optional = false)
    private EstadosGeneral idEstado;

    @JoinColumn(name = "Id_Usuario", referencedColumnName = "Id_Usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuariosComercios usuariosComercios;

    public SolicitudMiPago() {
    }

    /**
     * @param solicitudMiPagoPK
     */
    public SolicitudMiPago( SolicitudMiPagoPK solicitudMiPagoPK ) {
        this.solicitudMiPagoPK = solicitudMiPagoPK;
    }

    /**
     * @param solicitudMiPagoPK
     * @param fechaCreacion
     * @param cantidadMiPago 
     */
    public SolicitudMiPago( SolicitudMiPagoPK solicitudMiPagoPK, Date fechaCreacion, int cantidadMiPago ) {
        this.solicitudMiPagoPK = solicitudMiPagoPK;
        this.fechaCreacion = fechaCreacion;
        this.cantidadMiPago = cantidadMiPago;
    }

    /**
     * @param idSolicitud
     * @param idUsuario 
     */
    public SolicitudMiPago( String idSolicitud, int idUsuario ) {
        this.solicitudMiPagoPK = new SolicitudMiPagoPK( idSolicitud, idUsuario );
    }

    /**
     * @return SolicitudMiPagoPK
     */
    public SolicitudMiPagoPK getSolicitudMiPagoPK() {
        return solicitudMiPagoPK;
    }

    /**
     * @param solicitudMiPagoPK
     */
    public void setSolicitudMiPagoPK( SolicitudMiPagoPK solicitudMiPagoPK ) {
        this.solicitudMiPagoPK = solicitudMiPagoPK;
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
     * @return int
     */
    public int getCantidadMiPago() {
        return cantidadMiPago;
    }

    /**
     * @param cantidadMiPago
     */
    public void setCantidadMiPago( int cantidadMiPago ) {
        this.cantidadMiPago = cantidadMiPago;
    }

    /**
     * @return Date
     */
    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    /**
     * @param fechaAtencion
     */
    public void setFechaAtencion( Date fechaAtencion ) {
        this.fechaAtencion = fechaAtencion;
    }

    /**
     * @return String
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta
     */
    public void setRespuesta( String respuesta ) {
        this.respuesta = respuesta;
    }

    /**
     * @return Date
     */
    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    /**
     * @param fechaRespuesta
     */
    public void setFechaRespuesta( Date fechaRespuesta ) {
        this.fechaRespuesta = fechaRespuesta;
    }

    /**
     * @return EstadosGeneral
     */
    public EstadosGeneral getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado
     */
    public void setIdEstado( EstadosGeneral idEstado ) {
        this.idEstado = idEstado;
    }

    /**
     * @return UsuariosComercios
     */
    public UsuariosComercios getUsuariosComercios() {
        return usuariosComercios;
    }

    /**
     * @param usuariosComercios
     */
    public void setUsuariosComercios( UsuariosComercios usuariosComercios ) {
        this.usuariosComercios = usuariosComercios;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( solicitudMiPagoPK != null ? solicitudMiPagoPK.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof SolicitudMiPago ))
            return false;

        SolicitudMiPago other = (SolicitudMiPago) object;

        if (( this.solicitudMiPagoPK == null && other.solicitudMiPagoPK != null ) || ( this.solicitudMiPagoPK != null && !this.solicitudMiPagoPK.equals( other.solicitudMiPagoPK )))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.SolicitudMiPago[ solicitudMiPagoPK=" + solicitudMiPagoPK + " ]";
    }   
}