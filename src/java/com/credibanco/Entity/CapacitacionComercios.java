package com.credibanco.Entity;

import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
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
@Table(name = "capacitacion_comercios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CapacitacionComercios.findAll", query = "SELECT c FROM CapacitacionComercios c"),
    @NamedQuery(name = "CapacitacionComercios.findByIdCapacitacion", query = "SELECT c FROM CapacitacionComercios c WHERE c.idCapacitacion = :idCapacitacion"),
    @NamedQuery(name = "CapacitacionComercios.findByTituloCap", query = "SELECT c FROM CapacitacionComercios c WHERE c.tituloCap = :tituloCap"),
    @NamedQuery(name = "CapacitacionComercios.findByDescripcionCap", query = "SELECT c FROM CapacitacionComercios c WHERE c.descripcionCap = :descripcionCap"),
    @NamedQuery(name = "CapacitacionComercios.findByEnlaceCap", query = "SELECT c FROM CapacitacionComercios c WHERE c.enlaceCap = :enlaceCap"),
    @NamedQuery(name = "CapacitacionComercios.findByFechaCreacion", query = "SELECT c FROM CapacitacionComercios c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "CapacitacionComercios.findByVisible", query = "SELECT c FROM CapacitacionComercios c WHERE c.visible = :visible"),
    @NamedQuery(name = "CapacitacionComercios.findByVisitas", query = "SELECT c FROM CapacitacionComercios c WHERE c.visitas = :visitas")})
public class CapacitacionComercios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Capacitacion")
    private Integer idCapacitacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Titulo_Cap")
    private String tituloCap;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Descripcion_Cap")
    private String descripcionCap;

    @Column(name = "Enlace_Cap")
    private String enlaceCap;

    @Lob
    @Column(name = "Contenido_Cap")
    private byte[] contenidoCap;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visitas")
    private int visitas;

    @JoinColumn(name = "Categoria_Cap", referencedColumnName = "Id_Categoria")
    @ManyToOne(optional = false)
    private CategoriasCapacitacion categoriaCap;

    @JoinColumn(name = "Tipo_Capacitacion", referencedColumnName = "Id_Tipo")
    @ManyToOne(optional = false)
    private TipoCapacitacion tipoCapacitacion;

    public CapacitacionComercios() {
    }

    /**
     * @param idCapacitacion
     */
    public CapacitacionComercios( Integer idCapacitacion ) {
        this.idCapacitacion = idCapacitacion;
    }

    /**
     * @param idCapacitacion
     * @param tituloCap
     * @param descripcionCap
     * @param fechaCreacion
     * @param visible
     * @param visitas
     */
    public CapacitacionComercios( Integer idCapacitacion, String tituloCap, String descripcionCap, Date fechaCreacion, boolean visible, int visitas ) {
        this.idCapacitacion = idCapacitacion;
        this.tituloCap = tituloCap;
        this.descripcionCap = descripcionCap;
        this.fechaCreacion = fechaCreacion;
        this.visible = visible;
        this.visitas = visitas;
    }

    /**
     * @return Integer
     */
    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    /**
     * @param idCapacitacion
     */
    public void setIdCapacitacion( Integer idCapacitacion ) {
        this.idCapacitacion = idCapacitacion;
    }

    /**
     * @return String
     */
    public String getTituloCap() {
        return tituloCap;
    }

    /**
     * @param tituloCap
     */
    public void setTituloCap( String tituloCap ) {
        this.tituloCap = tituloCap;
    }

    /**
     * @return String
     */
    public String getDescripcionCap() {
        return descripcionCap;
    }

    /**
     * @param descripcionCap
     */
    public void setDescripcionCap( String descripcionCap ) {
        this.descripcionCap = descripcionCap;
    }

    /**
     * @return String
     */
    public String getEnlaceCap() {
        return enlaceCap;
    }

    /**
     * @param enlaceCap
     */
    public void setEnlaceCap( String enlaceCap ) {
        this.enlaceCap = enlaceCap;
    }

    /**
     * @return byte
     */
    public byte[] getContenidoCap() {
        return contenidoCap;
    }

    /**
     * @param contenidoCap
     */
    public void setContenidoCap( byte[] contenidoCap ) {
        this.contenidoCap = contenidoCap;
    }

    /**
     * @return Date
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion
     */
    public void setFechaCreacion( Date fechaCreacion ) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return boolean
     */
    public boolean getVisible() {
        return visible;
    }

    /**
     * @param visible
     */
    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    /**
     * @return int
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * @param visitas
     */
    public void setVisitas( int visitas ) {
        this.visitas = visitas;
    }

    /**
     * @return CategoriasCapacitacion
     */
    public CategoriasCapacitacion getCategoriaCap() {
        return categoriaCap;
    }

    /**
     * @param categoriaCap
     */
    public void setCategoriaCap( CategoriasCapacitacion categoriaCap ) {
        this.categoriaCap = categoriaCap;
    }

    /**
     * @return TipoCapacitacion
     */
    public TipoCapacitacion getTipoCapacitacion() {
        return tipoCapacitacion;
    }

    /**
     * @param tipoCapacitacion
     */
    public void setTipoCapacitacion( TipoCapacitacion tipoCapacitacion ) {
        this.tipoCapacitacion = tipoCapacitacion;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idCapacitacion != null ? idCapacitacion.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof CapacitacionComercios ))
            return false;

        CapacitacionComercios other = ( CapacitacionComercios ) object;

        if (( this.idCapacitacion == null && other.idCapacitacion != null ) || ( this.idCapacitacion != null && !this.idCapacitacion.equals( other.idCapacitacion )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.CapacitacionComercios[ idCapacitacion=" + idCapacitacion + " ]";
    }
}