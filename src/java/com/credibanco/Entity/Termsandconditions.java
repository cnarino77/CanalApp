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
@Table(name = "terminos_condiciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termsandconditions.findAll", query = "SELECT t FROM Termsandconditions t"),
    @NamedQuery(name = "Termsandconditions.findByIdTerms", query = "SELECT t FROM Termsandconditions t WHERE t.idTerms = :idTerms"),
    @NamedQuery(name = "Termsandconditions.findByTermsVersion", query = "SELECT t FROM Termsandconditions t WHERE t.termsVersion = :termsVersion"),
    @NamedQuery(name = "Termsandconditions.findByTermsText", query = "SELECT t FROM Termsandconditions t WHERE t.termsText = :termsText"),
    @NamedQuery(name = "Termsandconditions.findByFechaPublicacion", query = "SELECT t FROM Termsandconditions t WHERE t.fechaPublicacion = :fechaPublicacion")})
public class Termsandconditions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Terms")
    private Integer idTerms;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Terms_Version")
    private int termsVersion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Terms_Text")
    private String termsText;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Publicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;

    public Termsandconditions() {
    }

    /**
     * @param idTerms 
     */
    public Termsandconditions( Integer idTerms ) {
        this.idTerms = idTerms;
    }

    /**
     * @param idTerms
     * @param termsVersion
     * @param termsText
     * @param fechaPublicacion 
     */
    public Termsandconditions( Integer idTerms, int termsVersion, String termsText, Date fechaPublicacion ) {
        this.idTerms = idTerms;
        this.termsVersion = termsVersion;
        this.termsText = termsText;
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return Integer
     */
    public Integer getIdTerms() {
        return idTerms;
    }

    /**
     * @param idTerms
     */
    public void setIdTerms( Integer idTerms ) {
        this.idTerms = idTerms;
    }

    /**
     * @return int
     */
    public int getTermsVersion() {
        return termsVersion;
    }

    /**
     * @param termsVersion 
     */
    public void setTermsVersion( int termsVersion ) {
        this.termsVersion = termsVersion;
    }

    /**
     * @return String
     */
    public String getTermsText() {
        return termsText;
    }

    /**
     * @param termsText
     */
    public void setTermsText( String termsText ) {
        this.termsText = termsText;
    }

    /**
     * @return Date
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion
     */
    public void setFechaPublicacion( Date fechaPublicacion ) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idTerms != null ? idTerms.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Termsandconditions ))
            return false;

        Termsandconditions other = ( Termsandconditions ) object;

        if (( this.idTerms == null && other.idTerms != null ) || ( this.idTerms != null && !this.idTerms.equals( other.idTerms )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.Termsandconditions[ idTerms=" + idTerms + " ]";
    }   
}