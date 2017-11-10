package dao;

import java.util.Date;

/**
 * @author Daniel Moreno
 * @version 1.0
 */
public class MensajePushDao {
    
    private Integer idMensaje;

    private String messageTitle;

    private String messageText;

    private int remitente;

    private String destinatarios;

    private Integer destinoCiudad;

    private Integer destinoMCC;

    private String fechaEnvio;

    private boolean leido;

    private Integer messageCapacitacion;

    private String messageEnlace;

    public MensajePushDao() {
    }

    /**
     * @param idMensaje
     * @param messageTitle
     * @param messageText
     * @param remitente
     * @param destinatarios
     * @param destinoCiudad
     * @param destinoMCC
     * @param fechaEnvio
     * @param leido 
     */
    public MensajePushDao( Integer idMensaje, String messageTitle, String messageText, int remitente, String destinatarios, Integer destinoCiudad, Integer destinoMCC, String fechaEnvio, boolean leido ) {
        this.idMensaje = idMensaje;
        this.messageTitle = messageTitle;
        this.messageText = messageText;
        this.remitente = remitente;
        this.destinatarios = destinatarios;
        this.destinoCiudad = destinoCiudad;
        this.destinoMCC = destinoMCC;
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
     * @return String
     */
    public String getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * @param fechaEnvio 
     */
    public void setFechaEnvio( String fechaEnvio ) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * @return boolean
     */
    public boolean isLeido() {
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
}