package com.credibanco.Entity;

import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Lob;
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
@Table(name = "parametros_globales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrosGlobales.findAll", query = "SELECT p FROM ParametrosGlobales p"),
    @NamedQuery(name = "ParametrosGlobales.findByParameterKey", query = "SELECT p FROM ParametrosGlobales p WHERE p.parameterKey = :parameterKey"),
    @NamedQuery(name = "ParametrosGlobales.findByParameterValue", query = "SELECT p FROM ParametrosGlobales p WHERE p.parameterValue = :parameterValue")})
public class ParametrosGlobales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "parameter_Key")
    private String parameterKey;

    @Basic(optional = false)
    @NotNull
    @Column(name = "parameter_Value")
    private String parameterValue;

    @Lob
    @Column(name = "parameter_Value_Two")
    private byte[] parameterValueTwo;

    @Column(name = "parameter_Modification_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date parameterModificationDate;

    public ParametrosGlobales() {
    }

    /**
     * @param parameterKey
     */
    public ParametrosGlobales( String parameterKey ) {
        this.parameterKey = parameterKey;
    }

    /**
     * @param parameterKey
     * @param parameterValue
     */
    public ParametrosGlobales( String parameterKey, String parameterValue ) {
        this.parameterKey = parameterKey;
        this.parameterValue = parameterValue;
    }

    /**
     * @return String
     */
    public String getParameterKey() {
        return parameterKey;
    }

    /**
     * @param parameterKey
     */
    public void setParameterKey( String parameterKey ) {
        this.parameterKey = parameterKey;
    }

    /**
     * @return String
     */
    public String getParameterValue() {
        return parameterValue;
    }

    /**
     * @param parameterValue
     */
    public void setParameterValue( String parameterValue ) {
        this.parameterValue = parameterValue;
    }

    /**
     * @return byte
     */
    public byte[] getParameterValueTwo() {
        return parameterValueTwo;
    }

    /**
     * @param parameterValueTwo
     */
    public void setParameterValueTwo( byte[] parameterValueTwo ) {
        this.parameterValueTwo = parameterValueTwo;
    }

    /**
     * @return Date
     */
    public Date getParameterModificationDate() {
        return parameterModificationDate;
    }

    /**
     * @param parameterModificationDate
     */
    public void setParameterModificationDate( Date parameterModificationDate ) {
        this.parameterModificationDate = parameterModificationDate;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( parameterKey != null ? parameterKey.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof ParametrosGlobales ))
            return false;

        ParametrosGlobales other = ( ParametrosGlobales ) object;

        if (( this.parameterKey == null && other.parameterKey != null ) || ( this.parameterKey != null && !this.parameterKey.equals( other.parameterKey )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.ParametrosGlobales[ parameterKey=" + parameterKey + " ]";
    }
}