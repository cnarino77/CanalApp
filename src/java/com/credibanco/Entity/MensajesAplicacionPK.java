package com.credibanco.Entity;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Basic;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Embeddable
public class MensajesAplicacionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Message_Key")
    private String messageKey;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Message_Group")
    private int messageGroup;

    public MensajesAplicacionPK() {
    }

    /**
     * @param messageKey
     * @param messageGroup
     */
    public MensajesAplicacionPK( String messageKey, int messageGroup ) {
        this.messageKey = messageKey;
        this.messageGroup = messageGroup;
    }

    /**
     * @return String
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * @param messageKey
     */
    public void setMessageKey( String messageKey ) {
        this.messageKey = messageKey;
    }

    /**
     * @return int
     */
    public int getMessageGroup() {
        return messageGroup;
    }

    /**
     * @param messageGroup
     */
    public void setMessageGroup( int messageGroup ) {
        this.messageGroup = messageGroup;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( messageKey != null ? messageKey.hashCode() : 0 );
        hash += ( int ) messageGroup;
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof MensajesAplicacionPK ))
            return false;

        MensajesAplicacionPK other = ( MensajesAplicacionPK ) object;

        if (( this.messageKey == null && other.messageKey != null ) || ( this.messageKey != null && !this.messageKey.equals( other.messageKey )))
            return false;

        if ( this.messageGroup != other.messageGroup )
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.MensajesAplicacionPK[ messageKey=" + messageKey + ", messageGroup=" + messageGroup + " ]";
    }
}