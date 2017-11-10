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
@Table(name = "reporte_Fallas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteFallas.findAll", query = "SELECT r FROM ReporteFallas r"),
    @NamedQuery(name = "ReporteFallas.findByIdSolicitud", query = "SELECT r FROM ReporteFallas r WHERE r.reporteFallasPK.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "ReporteFallas.findByIdUsuario", query = "SELECT r FROM ReporteFallas r WHERE r.reporteFallasPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "ReporteFallas.findByFechaCreacion", query = "SELECT r FROM ReporteFallas r WHERE r.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ReporteFallas.findByDescripcion", query = "SELECT r FROM ReporteFallas r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "ReporteFallas.findByFechaCierre", query = "SELECT r FROM ReporteFallas r WHERE r.fechaCierre = :fechaCierre")})
public class ReporteFallas implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ReporteFallasPK reporteFallasPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Fecha_Cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;

    @JoinColumn(name = "Id_Estado", referencedColumnName = "id_Estado")
    @ManyToOne(optional = false)
    private EstadosGeneral idEstado;

    @JoinColumn(name = "Tipificacion", referencedColumnName = "Id_Tipificacion")
    @ManyToOne(optional = false)
    private TipificacionSM tipificacion;

    @JoinColumn(name = "Tipo_Tecnologia", referencedColumnName = "Id_Tipo_Pos")
    @ManyToOne(optional = false)
    private TipoPOS tipoTecnologia;

    @JoinColumn(name = "Id_Usuario", referencedColumnName = "Id_Usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuariosComercios usuariosComercios;

    public ReporteFallas() {
    }

    /**
     * @param reporteFallasPK
     */
    public ReporteFallas( ReporteFallasPK reporteFallasPK ) {
        this.reporteFallasPK = reporteFallasPK;
    }

    /**
     * @param reporteFallasPK
     * @param fechaCreacion
     * @param descripcion 
     */
    public ReporteFallas( ReporteFallasPK reporteFallasPK, Date fechaCreacion, String descripcion ) {
        this.reporteFallasPK = reporteFallasPK;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
    }

    /**
     * @param idSolicitud
     * @param idUsuario 
     */
    public ReporteFallas( String idSolicitud, int idUsuario ) {
        this.reporteFallasPK = new ReporteFallasPK( idSolicitud, idUsuario );
    }

    /**
     * @return ReporteFallasPK
     */
    public ReporteFallasPK getReporteFallasPK() {
        return reporteFallasPK;
    }

    /**
     * @param reporteFallasPK
     */
    public void setReporteFallasPK( ReporteFallasPK reporteFallasPK ) {
        this.reporteFallasPK = reporteFallasPK;
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
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     */
    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    /**
     * @return Date
     */
    public Date getFechaCierre() {
        return fechaCierre;
    }

    /**
     * @param fechaCierre
     */
    public void setFechaCierre( Date fechaCierre ) {
        this.fechaCierre = fechaCierre;
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
     * @return TipificacionSM
     */
    public TipificacionSM getTipificacion() {
        return tipificacion;
    }

    /**
     * @param tipificacion
     */
    public void setTipificacion( TipificacionSM tipificacion ) {
        this.tipificacion = tipificacion;
    }

    /**
     * @return TipoPOS
     */
    public TipoPOS getTipoTecnologia() {
        return tipoTecnologia;
    }

    /**
     * @param tipoTecnologia
     */
    public void setTipoTecnologia( TipoPOS tipoTecnologia ) {
        this.tipoTecnologia = tipoTecnologia;
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
        hash += ( reporteFallasPK != null ? reporteFallasPK.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof ReporteFallas ))
            return false;

        ReporteFallas other = ( ReporteFallas ) object;

        if (( this.reporteFallasPK == null && other.reporteFallasPK != null ) || ( this.reporteFallasPK != null && !this.reporteFallasPK.equals( other.reporteFallasPK )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.ReporteFallas[ reporteFallasPK=" + reporteFallasPK + " ]";
    }
}