package com.credibanco.Entity;

import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "mensajes_push")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MensajesPush.findAll", query = "SELECT m FROM MensajesPush m"),
    @NamedQuery(name = "MensajesPush.findByIdMensaje", query = "SELECT m FROM MensajesPush m WHERE m.idMensaje = :idMensaje"),
    @NamedQuery(name = "MensajesPush.findByMessageTitle", query = "SELECT m FROM MensajesPush m WHERE m.messageTitle = :messageTitle"),
    @NamedQuery(name = "MensajesPush.findByMessageText", query = "SELECT m FROM MensajesPush m WHERE m.messageText = :messageText"),
    @NamedQuery(name = "MensajesPush.findByRemitente", query = "SELECT m FROM MensajesPush m WHERE m.remitente = :remitente"),
    @NamedQuery(name = "MensajesPush.findByDestinatarios", query = "SELECT m FROM MensajesPush m WHERE m.destinatarios = :destinatarios"),
    @NamedQuery(name = "MensajesPush.findByDestinoCiudad", query = "SELECT m FROM MensajesPush m WHERE m.destinoCiudad = :destinoCiudad"),
    @NamedQuery(name = "MensajesPush.findByDestinoMCC", query = "SELECT m FROM MensajesPush m WHERE m.destinoMCC = :destinoMCC"),
    @NamedQuery(name = "MensajesPush.findByFechaEnvio", query = "SELECT m FROM MensajesPush m WHERE m.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "MensajesPush.findByLeido", query = "SELECT m FROM MensajesPush m WHERE m.leido = :leido"),
    @NamedQuery(name = "MensajesPush.findByMessageCapacitacion", query = "SELECT m FROM MensajesPush m WHERE m.messageCapacitacion = :messageCapacitacion"),
    @NamedQuery(name = "MensajesPush.findByMessageEnlace", query = "SELECT m FROM MensajesPush m WHERE m.messageEnlace = :messageEnlace")})
public class MensajesPush implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Mensaje")
    private Integer idMensaje;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Message_Title")
    private String messageTitle;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Message_Text")
    private String messageText;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Remitente")
    private int remitente;

    @Column(name = "Destinatarios")
    private String destinatarios;

    @Column(name = "Destino_Ciudad")
    private Integer destinoCiudad;

    @Column(name = "Destino_MCC")
    private Integer destinoMCC;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Leido")
    private boolean leido;

    @Column(name = "Message_Capacitacion")
    private Integer messageCapacitacion;

    @Column(name = "Message_Enlace")
    private String messageEnlace;

    public MensajesPush() {
    }

    /**
     * @param idMensaje
     */
    public MensajesPush( Integer idMensaje ) {
        this.idMensaje = idMensaje;
    }

    /**
     * @param idMensaje
     * @param messageTitle
     * @param messageText
     * @param remitente
     * @param fechaEnvio
     * @param leido
     */
    public MensajesPush( Integer idMensaje, String messageTitle, String messageText, int remitente, Date fechaEnvio, boolean leido ) {
        this.idMensaje = idMensaje;
        this.messageTitle = messageTitle;
        this.messageText = messageText;
        this.remitente = remitente;
        this.fechaEnvio = fechaEnvio;
        this.leido = leido;
    }

    /**
     * @return Integer
     */
    public Integer getIdMensaje() {
        return idMensaje;
    }

    /**
     * @param idMensaje
     */
    public void setIdMensaje( Integer idMensaje ) {
        this.idMensaje = idMensaje;
    }

    /**
     * @return String
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * @param messageTitle
     */
    public void setMessageTitle( String messageTitle ) {
        this.messageTitle = messageTitle;
    }

    /**
     * @return String
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * @param messageText
     */
    public void setMessageText( String messageText ) {
        this.messageText = messageText;
    }

    /**
     * @return int
     */
    public int getRemitente() {
        return remitente;
    }

    /**
     * @param remitente
     */
    public void setRemitente( int remitente ) {
        this.remitente = remitente;
    }

    /**
     * @return String
     */
    public String getDestinatarios() {
        return destinatarios;
    }

    /**
     * @param destinatarios
     */
    public void setDestinatarios( String destinatarios ) {
        this.destinatarios = destinatarios;
    }

    /**
     * @return Integer
     */
    public Integer getDestinoCiudad() {
        return destinoCiudad;
    }

    /**
     * @param destinoCiudad
     */
    public void setDestinoCiudad( Integer destinoCiudad ) {
        this.destinoCiudad = destinoCiudad;
    }

    /**
     * @return Integer
     */
    public Integer getDestinoMCC() {
        return destinoMCC;
    }

    /**
     * @param destinoMCC
     */
    public void setDestinoMCC( Integer destinoMCC ) {
        this.destinoMCC = destinoMCC;
    }

    /**
     * @return Date
     */
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * @param fechaEnvio
     */
    public void setFechaEnvio( Date fechaEnvio ) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * @return boolean
     */
    public boolean getLeido() {
        return leido;
    }

    /**
     * @param leido
     */
    public void setLeido( boolean leido ) {
        this.leido = leido;
    }

    /**
     * @return Integer
     */
    public Integer getMessageCapacitacion() {
        return messageCapacitacion;
    }

    /**
     * @param messageCapacitacion
     */
    public void setMessageCapacitacion( Integer messageCapacitacion ) {
        this.messageCapacitacion = messageCapacitacion;
    }

    /**
     * @return String
     */
    public String getMessageEnlace() {
        return messageEnlace;
    }

    /**
     * @param messageEnlace
     */
    public void setMessageEnlace( String messageEnlace ) {
        this.messageEnlace = messageEnlace;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idMensaje != null ? idMensaje.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof MensajesPush ))
            return false;

        MensajesPush other = ( MensajesPush ) object;

        if (( this.idMensaje == null && other.idMensaje != null ) || ( this.idMensaje != null && !this.idMensaje.equals( other.idMensaje )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.MensajesPush[ idMensaje=" + idMensaje + " ]";
    }
}