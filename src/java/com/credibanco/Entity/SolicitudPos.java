package com.credibanco.Entity;

import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Basic;

import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "solicitud_Pos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudPos.findAll", query = "SELECT s FROM SolicitudPos s"),
    @NamedQuery(name = "SolicitudPos.findByIdSolicitud", query = "SELECT s FROM SolicitudPos s WHERE s.solicitudPosPK.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "SolicitudPos.findByIdUsuario", query = "SELECT s FROM SolicitudPos s WHERE s.solicitudPosPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "SolicitudPos.findByFechaCreacion", query = "SELECT s FROM SolicitudPos s WHERE s.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "SolicitudPos.findByCantidadPOS", query = "SELECT s FROM SolicitudPos s WHERE s.cantidadPOS = :cantidadPOS"),
    @NamedQuery(name = "SolicitudPos.findByHorarioAM", query = "SELECT s FROM SolicitudPos s WHERE s.horarioAM = :horarioAM"),
    @NamedQuery(name = "SolicitudPos.findByRespuesta", query = "SELECT s FROM SolicitudPos s WHERE s.respuesta = :respuesta"),
    @NamedQuery(name = "SolicitudPos.findByFechaRespuesta", query = "SELECT s FROM SolicitudPos s WHERE s.fechaRespuesta = :fechaRespuesta"),
    @NamedQuery(name = "SolicitudPos.findByDireccionStand", query = "SELECT s FROM SolicitudPos s WHERE s.direccionStand = :direccionStand"),
    @NamedQuery(name = "SolicitudPos.findByUbicacionStand", query = "SELECT s FROM SolicitudPos s WHERE s.ubicacionStand = :ubicacionStand"),
    @NamedQuery(name = "SolicitudPos.findByStand", query = "SELECT s FROM SolicitudPos s WHERE s.stand = :stand"),
    @NamedQuery(name = "SolicitudPos.findByNombreEvento", query = "SELECT s FROM SolicitudPos s WHERE s.nombreEvento = :nombreEvento"),
    @NamedQuery(name = "SolicitudPos.findByFechaInicioEvento", query = "SELECT s FROM SolicitudPos s WHERE s.fechaInicioEvento = :fechaInicioEvento"),
    @NamedQuery(name = "SolicitudPos.findByFechaRetiro", query = "SELECT s FROM SolicitudPos s WHERE s.fechaRetiro = :fechaRetiro")})
public class SolicitudPos implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected SolicitudPosPK solicitudPosPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad_POS")
    private int cantidadPOS;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Horario_AM")
    private boolean horarioAM;

    @Column(name = "Respuesta")
    private String respuesta;

    @Column(name = "Fecha_Respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;

    @Column(name = "Direccion_Stand")
    private String direccionStand;

    @Column(name = "Ubicacion_Stand")
    private String ubicacionStand;

    @Column(name = "Stand")
    private String stand;

    @Column(name = "Nombre_Evento")
    private String nombreEvento;

    @Column(name = "Fecha_Inicio_Evento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioEvento;

    @Column(name = "Fecha_Retiro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetiro;

    @JoinColumn(name = "Destino_POS", referencedColumnName = "Id_Destino")
    @ManyToOne(optional = false)
    private DestinoPOS destinoPOS;

    @JoinColumn(name = "Id_Estado", referencedColumnName = "id_Estado")
    @ManyToOne(optional = false)
    private EstadosGeneral idEstado;

    @JoinColumn(name = "Tipo_POS", referencedColumnName = "Id_Tipo_Pos")
    @ManyToOne(optional = false)
    private TipoPOS tipoPOS;

    @JoinColumn(name = "Id_Usuario", referencedColumnName = "Id_Usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuariosComercios usuariosComercios;

    public SolicitudPos() {
    }

    /**
     * @param solicitudPosPK 
     */
    public SolicitudPos( SolicitudPosPK solicitudPosPK ) {
        this.solicitudPosPK = solicitudPosPK;
    }

    /**
     * @param solicitudPosPK
     * @param fechaCreacion
     * @param cantidadPOS
     * @param horarioAM
     * @param direccionStand
     * @param ubicacionStand
     * @param nombreEvento
     * @param fechaInicioEvento
     * @param fechaRetiro
     * @param destinoPOS
     * @param idEstado
     * @param tipoPOS
     * @param usuariosComercios 
     */
    public SolicitudPos( SolicitudPosPK solicitudPosPK, Date fechaCreacion, int cantidadPOS, boolean horarioAM, String direccionStand, String ubicacionStand, String nombreEvento, Date fechaInicioEvento, Date fechaRetiro, DestinoPOS destinoPOS, EstadosGeneral idEstado, TipoPOS tipoPOS, UsuariosComercios usuariosComercios ) {
        this.solicitudPosPK = solicitudPosPK;
        this.fechaCreacion = fechaCreacion;
        this.cantidadPOS = cantidadPOS;
        this.horarioAM = horarioAM;
        this.direccionStand = direccionStand;
        this.ubicacionStand = ubicacionStand;
        this.nombreEvento = nombreEvento;
        this.fechaInicioEvento = fechaInicioEvento;
        this.fechaRetiro = fechaRetiro;
        this.destinoPOS = destinoPOS;
        this.idEstado = idEstado;
        this.tipoPOS = tipoPOS;
        this.usuariosComercios = usuariosComercios;
    }

    /**
     * @param solicitudPosPK
     * @param fechaCreacion
     * @param cantidadPOS
     * @param horarioAM
     * @param destinoPOS
     * @param idEstado
     * @param tipoPOS 
     */
    public SolicitudPos( SolicitudPosPK solicitudPosPK, Date fechaCreacion, int cantidadPOS, boolean horarioAM, DestinoPOS destinoPOS, EstadosGeneral idEstado, TipoPOS tipoPOS ) {
        this.solicitudPosPK = solicitudPosPK;
        this.fechaCreacion = fechaCreacion;
        this.cantidadPOS = cantidadPOS;
        this.horarioAM = horarioAM;
        this.destinoPOS = destinoPOS;
        this.idEstado = idEstado;
        this.tipoPOS = tipoPOS;
    }

    /**
     * @param solicitudPosPK
     * @param fechaCreacion
     * @param cantidadPOS
     * @param horarioAM 
     */
    public SolicitudPos( SolicitudPosPK solicitudPosPK, Date fechaCreacion, int cantidadPOS, boolean horarioAM ) {
        this.solicitudPosPK = solicitudPosPK;
        this.fechaCreacion = fechaCreacion;
        this.cantidadPOS = cantidadPOS;
        this.horarioAM = horarioAM;
    }

    /**
     * @param idSolicitud
     * @param idUsuario 
     */
    public SolicitudPos( String idSolicitud, int idUsuario ) {
        this.solicitudPosPK = new SolicitudPosPK( idSolicitud, idUsuario );
    }

    /**
     * @return SolicitudPosPK
     */
    public SolicitudPosPK getSolicitudPosPK() {
        return solicitudPosPK;
    }

    /**
     * @param solicitudPosPK 
     */
    public void setSolicitudPosPK( SolicitudPosPK solicitudPosPK ) {
        this.solicitudPosPK = solicitudPosPK;
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
    public int getCantidadPOS() {
        return cantidadPOS;
    }

    /**
     * @param cantidadPOS 
     */
    public void setCantidadPOS( int cantidadPOS ) {
        this.cantidadPOS = cantidadPOS;
    }

    /**
     * @return boolean
     */
    public boolean getHorarioAM() {
        return horarioAM;
    }

    /**
     * @param horarioAM 
     */
    public void setHorarioAM( boolean horarioAM ) {
        this.horarioAM = horarioAM;
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
     * @return String
     */
    public String getDireccionStand() {
        return direccionStand;
    }

    /**
     * @param direccionStand
     */
    public void setDireccionStand( String direccionStand ) {
        this.direccionStand = direccionStand;
    }

    /**
     * @return String
     */
    public String getUbicacionStand() {
        return ubicacionStand;
    }

    /**
     * @param ubicacionStand
     */
    public void setUbicacionStand( String ubicacionStand ) {
        this.ubicacionStand = ubicacionStand;
    }

    /**
     * @return String
     */
    public String getStand() {
        return stand;
    }

    /**
     * @param stand
     */
    public void setStand( String stand ) {
        this.stand = stand;
    }

    /**
     * @return String
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * @param nombreEvento
     */
    public void setNombreEvento( String nombreEvento ) {
        this.nombreEvento = nombreEvento;
    }

    /**
     * @return Date
     */
    public Date getFechaInicioEvento() {
        return fechaInicioEvento;
    }

    /**
     * @param fechaInicioEvento
     */
    public void setFechaInicioEvento( Date fechaInicioEvento ) {
        this.fechaInicioEvento = fechaInicioEvento;
    }

    /**
     * @return Date
     */
    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    /**
     * @param fechaRetiro
     */
    public void setFechaRetiro( Date fechaRetiro ) {
        this.fechaRetiro = fechaRetiro;
    }

    /**
     * @return DestinoPOS
     */
    public DestinoPOS getDestinoPOS() {
        return destinoPOS;
    }

    /**
     * @param destinoPOS 
     */
    public void setDestinoPOS( DestinoPOS destinoPOS ) {
        this.destinoPOS = destinoPOS;
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
     * @return TipoPOS
     */
    public TipoPOS getTipoPOS() {
        return tipoPOS;
    }

    /**
     * @param tipoPOS
     */
    public void setTipoPOS( TipoPOS tipoPOS ) {
        this.tipoPOS = tipoPOS;
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
        hash += ( solicitudPosPK != null ? solicitudPosPK.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof SolicitudPos ))
            return false;

        SolicitudPos other = ( SolicitudPos ) object;

        if (( this.solicitudPosPK == null && other.solicitudPosPK != null ) || ( this.solicitudPosPK != null && !this.solicitudPosPK.equals( other.solicitudPosPK )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.SolicitudPos[ solicitudPosPK=" + solicitudPosPK + " ]";
    }
}