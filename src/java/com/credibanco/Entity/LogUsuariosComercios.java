package com.credibanco.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "log_Usuarios_Comercios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogUsuariosComercios.findAll", query = "SELECT l FROM LogUsuariosComercios l"),
    @NamedQuery(name = "LogUsuariosComercios.findByIdLog", query = "SELECT l FROM LogUsuariosComercios l WHERE l.idLog = :idLog"),
    @NamedQuery(name = "LogUsuariosComercios.findByLogAccion", query = "SELECT l FROM LogUsuariosComercios l WHERE l.logAccion = :logAccion"),
    @NamedQuery(name = "LogUsuariosComercios.findByItemAccion", query = "SELECT l FROM LogUsuariosComercios l WHERE l.itemAccion = :itemAccion"),
    @NamedQuery(name = "LogUsuariosComercios.findByIp", query = "SELECT l FROM LogUsuariosComercios l WHERE l.ip = :ip"),
    @NamedQuery(name = "LogUsuariosComercios.findByUsuario", query = "SELECT l FROM LogUsuariosComercios l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "LogUsuariosComercios.findByLogDate", query = "SELECT l FROM LogUsuariosComercios l WHERE l.logDate = :logDate")})
public class LogUsuariosComercios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id_Log")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idLog;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Log_Accion")
    private String logAccion;

    @Column(name = "Item_Accion")
    private String itemAccion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "IP")
    private String ip;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Usuario")
    private String usuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Log_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate;

    public LogUsuariosComercios() {
    }

    /**
     * @param idLog
     */
    public LogUsuariosComercios( Integer idLog ) {
        this.idLog = idLog;
    }

    /**
     * @param idLog
     * @param logAccion
     * @param ip
     * @param usuario
     * @param logDate
     */
    public LogUsuariosComercios( Integer idLog, String logAccion, String ip, String usuario, Date logDate ) {
        this.idLog = idLog;
        this.logAccion = logAccion;
        this.ip = ip;
        this.usuario = usuario;
        this.logDate = logDate;
    }

    /**
     * @param logAccion
     * @param ip
     * @param usuario
     * @param logDate
     * @param itemAccion
     */
    public LogUsuariosComercios( String logAccion, String ip, String usuario, Date logDate, String itemAccion ) {
        this.logAccion = logAccion;
        this.ip = ip;
        this.usuario = usuario;
        this.logDate = logDate;
        this.itemAccion = itemAccion;
    }

    /** 
     * @return Integer
     */
    public Integer getIdLog() {
        return idLog;
    }

    /**
     * @param idLog
     */
    public void setIdLog( Integer idLog ) {
        this.idLog = idLog;
    }

    /**
     * @return String
     */
    public String getLogAccion() {
        return logAccion;
    }

    /**
     * @param logAccion
     */
    public void setLogAccion( String logAccion ) {
        this.logAccion = logAccion;
    }

    /**
     * @return String
     */
    public String getItemAccion() {
        return itemAccion;
    }

    /**
     * @param itemAccion
     */
    public void setItemAccion( String itemAccion ) {
        this.itemAccion = itemAccion;
    }

    /**
     * @return String
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp( String ip ) {
        this.ip = ip;
    }

    /**
     * @return String
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     */
    public void setUsuario( String usuario ) {
        this.usuario = usuario;
    }

    /**
     * @return Date
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * @param logDate
     */
    public void setLogDate( Date logDate ) {
        this.logDate = logDate;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idLog != null ? idLog.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof LogUsuariosComercios ))
            return false;

        LogUsuariosComercios other = ( LogUsuariosComercios ) object;

        if (( this.idLog == null && other.idLog != null ) || ( this.idLog != null && !this.idLog.equals( other.idLog )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.LogUsuariosComercios[ idLog=" + idLog + " ]";
    }
}