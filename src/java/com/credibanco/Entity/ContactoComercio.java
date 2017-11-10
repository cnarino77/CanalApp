package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.JoinColumns;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;


/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "contacto_comercio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactoComercio.findAll", query = "SELECT c FROM ContactoComercio c"),
    @NamedQuery(name = "ContactoComercio.findByIdContacto", query = "SELECT c FROM ContactoComercio c WHERE c.idContacto = :idContacto"),
    @NamedQuery(name = "ContactoComercio.findByNombreContacto", query = "SELECT c FROM ContactoComercio c WHERE c.nombreContacto = :nombreContacto"),
    @NamedQuery(name = "ContactoComercio.findByApellidoContacto", query = "SELECT c FROM ContactoComercio c WHERE c.apellidoContacto = :apellidoContacto"),
    @NamedQuery(name = "ContactoComercio.findByMailContacto", query = "SELECT c FROM ContactoComercio c WHERE c.mailContacto = :mailContacto"),
    @NamedQuery(name = "ContactoComercio.findByFijoContacto", query = "SELECT c FROM ContactoComercio c WHERE c.fijoContacto = :fijoContacto"),
    @NamedQuery(name = "ContactoComercio.findByMovilContacto", query = "SELECT c FROM ContactoComercio c WHERE c.movilContacto = :movilContacto")})
public class ContactoComercio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Contacto")
    private String idContacto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombre_Contacto")
    private String nombreContacto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Apellido_Contacto")
    private String apellidoContacto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Mail_Contacto")
    private String mailContacto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fijo_Contacto")
    private String fijoContacto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Movil_Contacto")
    private String movilContacto;

    @JoinColumns({
        @JoinColumn(name = "Nit_Comercio", referencedColumnName = "Nit_Comercio"),
        @JoinColumn(name = "Codigo_Unico", referencedColumnName = "Codigo_Unico")})
    @ManyToOne(optional = false)
    private DatosComercio datosComercio;

    @JoinColumn(name = "Tipo_Id_Contacto", referencedColumnName = "id_Tipo")
    @ManyToOne(optional = false)
    private TipodocId tipoIdContacto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContacto")
    private Collection<UsuariosComercios> usuariosComerciosCollection;

    public ContactoComercio() {
    }

    /**
     * @param idContacto 
     */
    public ContactoComercio( String idContacto ) {
        this.idContacto = idContacto;
    }

    /**
     * @param idContacto
     * @param nombreContacto
     * @param apellidoContacto
     * @param mailContacto
     * @param fijoContacto
     * @param movilContacto
     * @param datosComercio
     * @param tipoIdContacto 
     */
    public ContactoComercio( String idContacto, String nombreContacto, String apellidoContacto, String mailContacto, String fijoContacto, String movilContacto, DatosComercio datosComercio, TipodocId tipoIdContacto ) {
        this.idContacto = idContacto;
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.mailContacto = mailContacto;
        this.fijoContacto = fijoContacto;
        this.movilContacto = movilContacto;
        this.datosComercio = datosComercio;
        this.tipoIdContacto = tipoIdContacto;
    }

    /**
     * @return String
     */
    public String getIdContacto() {
        return idContacto;
    }

    /**
     * @param idContacto 
     */
    public void setIdContacto( String idContacto ) {
        this.idContacto = idContacto;
    }

    /**
     * @return String
     */
    public String getNombreContacto() {
        return nombreContacto;
    }

    /**
     * @param nombreContacto 
     */
    public void setNombreContacto( String nombreContacto ) {
        this.nombreContacto = nombreContacto;
    }

    /**
     * @return String
     */
    public String getApellidoContacto() {
        return apellidoContacto;
    }

    /**
     * @param apellidoContacto 
     */
    public void setApellidoContacto( String apellidoContacto ) {
        this.apellidoContacto = apellidoContacto;
    }

    /**
     * @return String
     */
    public String getMailContacto() {
        return mailContacto;
    }

    /**
     * @param mailContacto 
     */
    public void setMailContacto( String mailContacto ) {
        this.mailContacto = mailContacto;
    }

    /**
     * @return String
     */
    public String getFijoContacto() {
        return fijoContacto;
    }

    /**
     * @param fijoContacto 
     */
    public void setFijoContacto( String fijoContacto ) {
        this.fijoContacto = fijoContacto;
    }

    /**
     * @return 
     */
    public String getMovilContacto() {
        return movilContacto;
    }

    /**
     * @param movilContacto 
     */
    public void setMovilContacto( String movilContacto ) {
        this.movilContacto = movilContacto;
    }

    /**
     * @return DatosComercio
     */
    public DatosComercio getDatosComercio() {
        return datosComercio;
    }

    /**
     * @param datosComercio 
     */
    public void setDatosComercio( DatosComercio datosComercio ) {
        this.datosComercio = datosComercio;
    }

    /**
     * @return TipodocId
     */
    public TipodocId getTipoIdContacto() {
        return tipoIdContacto;
    }

    /**
     * @param tipoIdContacto 
     */
    public void setTipoIdContacto( TipodocId tipoIdContacto ) {
        this.tipoIdContacto = tipoIdContacto;
    }

    /**
     * @return Collection
     */
    @XmlTransient
    public Collection<UsuariosComercios> getUsuariosComerciosCollection() {
        return usuariosComerciosCollection;
    }

    /**
     * @param usuariosComerciosCollection 
     */
    public void setUsuariosComerciosCollection( Collection<UsuariosComercios> usuariosComerciosCollection ) {
        this.usuariosComerciosCollection = usuariosComerciosCollection;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idContacto != null ? idContacto.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof ContactoComercio ))
            return false;

        ContactoComercio other = ( ContactoComercio ) object;

        if (( this.idContacto == null && other.idContacto != null ) || ( this.idContacto != null && !this.idContacto.equals( other.idContacto )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Entity.ContactoComercio[ idContacto=" + idContacto + " ]";
    }
}