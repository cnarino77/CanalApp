package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.TemporalType;
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
 *
 * @author sami
 */
@Entity
@Table(name = "politica_tratamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoliticaTratamiento.findAll", query = "SELECT p FROM PoliticaTratamiento p"),
    @NamedQuery(name = "PoliticaTratamiento.findByIdPolicy", query = "SELECT p FROM PoliticaTratamiento p WHERE p.idPolicy = :idPolicy"),
    @NamedQuery(name = "PoliticaTratamiento.findByPolicyVersion", query = "SELECT p FROM PoliticaTratamiento p WHERE p.policyVersion = :policyVersion"),
    @NamedQuery(name = "PoliticaTratamiento.findByPolicyText", query = "SELECT p FROM PoliticaTratamiento p WHERE p.policyText = :policyText"),
    @NamedQuery(name = "PoliticaTratamiento.findByFechaPublicacion", query = "SELECT p FROM PoliticaTratamiento p WHERE p.fechaPublicacion = :fechaPublicacion")})
public class PoliticaTratamiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Policy")
    private Integer idPolicy;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Policy_Version")
    private int policyVersion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Policy_Text")
    private String policyText;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Publicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;

    public PoliticaTratamiento() {
    }

    /**
     * @param idPolicy
     */
    public PoliticaTratamiento( Integer idPolicy ) {
        this.idPolicy = idPolicy;
    }

    /**
     * @param idPolicy
     * @param policyVersion
     * @param policyText
     * @param fechaPublicacion
     */
    public PoliticaTratamiento( Integer idPolicy, int policyVersion, String policyText, Date fechaPublicacion ) {
        this.idPolicy = idPolicy;
        this.policyVersion = policyVersion;
        this.policyText = policyText;
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return Integer
     */
    public Integer getIdPolicy() {
        return idPolicy;
    }

    /**
     * @param idPolicy
     */
    public void setIdPolicy( Integer idPolicy ) {
        this.idPolicy = idPolicy;
    }

    /**
     * @return int
     */
    public int getPolicyVersion() {
        return policyVersion;
    }

    /**
     * @param policyVersion
     */
    public void setPolicyVersion( int policyVersion ) {
        this.policyVersion = policyVersion;
    }

    /**
     * @return String
     */
    public String getPolicyText() {
        return policyText;
    }

    /**
     * @param policyText
     */
    public void setPolicyText( String policyText ) {
        this.policyText = policyText;
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
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idPolicy != null ? idPolicy.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoliticaTratamiento)) {
            return false;
        }
        PoliticaTratamiento other = ( PoliticaTratamiento ) object;
        if (( this.idPolicy == null && other.idPolicy != null ) || ( this.idPolicy != null && !this.idPolicy.equals( other.idPolicy ))) {
            return false;
        }
        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.PoliticaTratamiento[ idPolicy=" + idPolicy + " ]";
    }   
}