package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;

import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "mensajes_aplicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MensajesAplicacion.findAll", query = "SELECT m FROM MensajesAplicacion m"),
    @NamedQuery(name = "MensajesAplicacion.findByMessageKey", query = "SELECT m FROM MensajesAplicacion m WHERE m.mensajesAplicacionPK.messageKey = :messageKey"),
    @NamedQuery(name = "MensajesAplicacion.findByMessageGroup", query = "SELECT m FROM MensajesAplicacion m WHERE m.mensajesAplicacionPK.messageGroup = :messageGroup"),
    @NamedQuery(name = "MensajesAplicacion.findByMessageText", query = "SELECT m FROM MensajesAplicacion m WHERE m.messageText = :messageText")})
public class MensajesAplicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected MensajesAplicacionPK mensajesAplicacionPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Message_Text")
    private String messageText;

    public MensajesAplicacion() {
    }

    /**
     * @param mensajesAplicacionPK
     */
    public MensajesAplicacion( MensajesAplicacionPK mensajesAplicacionPK ) {
        this.mensajesAplicacionPK = mensajesAplicacionPK;
    }

    /**
     * @param mensajesAplicacionPK
     * @param messageText
     */
    public MensajesAplicacion( MensajesAplicacionPK mensajesAplicacionPK, String messageText ) {
        this.mensajesAplicacionPK = mensajesAplicacionPK;
        this.messageText = messageText;
    }

    /**
     * @param messageKey
     * @param messageGroup
     */
    public MensajesAplicacion( String messageKey, int messageGroup ) {
        this.mensajesAplicacionPK = new MensajesAplicacionPK( messageKey, messageGroup );
    }

    /**
     * @return MensajesAplicacionPK
     */
    public MensajesAplicacionPK getMensajesAplicacionPK() {
        return mensajesAplicacionPK;
    }

    /**
     * @param mensajesAplicacionPK
     */
    public void setMensajesAplicacionPK( MensajesAplicacionPK mensajesAplicacionPK ) {
        this.mensajesAplicacionPK = mensajesAplicacionPK;
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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( mensajesAplicacionPK != null ? mensajesAplicacionPK.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof MensajesAplicacion ))
            return false;

        MensajesAplicacion other = ( MensajesAplicacion ) object;

        if (( this.mensajesAplicacionPK == null && other.mensajesAplicacionPK != null ) || ( this.mensajesAplicacionPK != null && !this.mensajesAplicacionPK.equals( other.mensajesAplicacionPK )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.MensajesAplicacion[ mensajesAplicacionPK=" + mensajesAplicacionPK + " ]";
    }
}