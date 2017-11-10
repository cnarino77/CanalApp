package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Samuel Zabala
 * @modify Daniel Moreno
 * @Version 1.1
 */
@Entity
@Table(name = "Tipificacion_SM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipificacionSM.findAll", query = "SELECT t FROM TipificacionSM t"),
    @NamedQuery(name = "TipificacionSM.findByIdTipificacion", query = "SELECT t FROM TipificacionSM t WHERE t.idTipificacion = :idTipificacion"),
    @NamedQuery(name = "TipificacionSM.findByTipificacion", query = "SELECT t FROM TipificacionSM t WHERE t.tipificacion = :tipificacion"),
    @NamedQuery(name = "TipificacionSM.findByTipoCliente", query = "SELECT t FROM TipificacionSM t WHERE t.tipoCliente = :tipoCliente"),
    @NamedQuery(name = "TipificacionSM.findByTipoSolicitud", query = "SELECT t FROM TipificacionSM t WHERE t.tipoSolicitud = :tipoSolicitud"),
    @NamedQuery(name = "TipificacionSM.findByProducto", query = "SELECT t FROM TipificacionSM t WHERE t.producto = :producto"),
    @NamedQuery(name = "TipificacionSM.findByClasificacion", query = "SELECT t FROM TipificacionSM t WHERE t.clasificacion = :clasificacion")})
public class TipificacionSM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Tipificacion")
    private Integer idTipificacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipificacion")
    private String tipificacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoCliente")
    private String tipoCliente;

    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoSolicitud")
    private String tipoSolicitud;

    @Basic(optional = false)
    @NotNull
    @Column(name = "producto")
    private String producto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "clasificacion")
    private String clasificacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TipificacionNombre")
    private String tipificacionNombre;

    public TipificacionSM() {
    }

    /**
     * @param idTipificacion 
     */
    public TipificacionSM( Integer idTipificacion ) {
        this.idTipificacion = idTipificacion;
    }

    /**
     * @param idTipificacion
     * @param tipificacion
     * @param tipoCliente
     * @param tipoSolicitud
     * @param producto
     * @param clasificacion 
     */
    public TipificacionSM( Integer idTipificacion, String tipificacion, String tipoCliente, String tipoSolicitud, String producto, String clasificacion ) {
        this.idTipificacion = idTipificacion;
        this.tipificacion = tipificacion;
        this.tipoCliente = tipoCliente;
        this.tipoSolicitud = tipoSolicitud;
        this.producto = producto;
        this.clasificacion = clasificacion;
    }

    /**
     * @return Integer
     */
    public Integer getIdTipificacion() {
        return idTipificacion;
    }

    /**
     * @param idTipificacion
     */
    public void setIdTipificacion( Integer idTipificacion ) {
        this.idTipificacion = idTipificacion;
    }

    /**
     * @return String
     */
    public String getTipificacion() {
        return tipificacion;
    }

    /**
     * @param tipificacion
     */
    public void setTipificacion( String tipificacion ) {
        this.tipificacion = tipificacion;
    }

    /**
     * @return String
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente
     */
    public void setTipoCliente( String tipoCliente ) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * @return String
     */
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    /**
     * @param tipoSolicitud
     */
    public void setTipoSolicitud( String tipoSolicitud ) {
        this.tipoSolicitud = tipoSolicitud;
    }

    /**
     * @return String
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto
     */
    public void setProducto( String producto ) {
        this.producto = producto;
    }

    /**
     * @return String
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion
     */
    public void setClasificacion( String clasificacion ) {
        this.clasificacion = clasificacion;
    }

    /**
     * @return String
     */
    public String getTipificacionNombre() {
        return tipificacionNombre;
    }

    /**
     * @param tipificacionNombre 
     */
    public void setTipificacionNombre( String tipificacionNombre ) {
        this.tipificacionNombre = tipificacionNombre;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idTipificacion != null ? idTipificacion.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof TipificacionSM ))
            return false;
        TipificacionSM other = (TipificacionSM) object;

        if (( this.idTipificacion == null && other.idTipificacion != null ) || ( this.idTipificacion != null && !this.idTipificacion.equals( other.idTipificacion )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.TipificacionSM[ idTipificacion=" + idTipificacion + " ]";
    }   
}