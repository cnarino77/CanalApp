package com.credibanco.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
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
@Table(name = "FAQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faq.findAll", query = "SELECT f FROM Faq f"),
    @NamedQuery(name = "Faq.findByIdPregunta", query = "SELECT f FROM Faq f WHERE f.idPregunta = :idPregunta"),
    @NamedQuery(name = "Faq.findByTituloPregunta", query = "SELECT f FROM Faq f WHERE f.tituloPregunta = :tituloPregunta"),
    @NamedQuery(name = "Faq.findByRespuestaPregunta", query = "SELECT f FROM Faq f WHERE f.respuestaPregunta = :respuestaPregunta"),
    @NamedQuery(name = "Faq.findByVersionPregunta", query = "SELECT f FROM Faq f WHERE f.versionPregunta = :versionPregunta"),
    @NamedQuery(name = "Faq.findByVisible", query = "SELECT f FROM Faq f WHERE f.visible = :visible"),
    @NamedQuery(name = "Faq.findByVisitas", query = "SELECT f FROM Faq f WHERE f.visitas = :visitas")})
public class Faq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Pregunta")
    private Integer idPregunta;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Titulo_Pregunta")
    private String tituloPregunta;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Respuesta_Pregunta")
    private String respuestaPregunta;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Version_Pregunta")
    private int versionPregunta;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Visitas")
    private long visitas;

    @JoinColumn(name = "Categoria_Pregunta", referencedColumnName = "Id_Categoria")
    @ManyToOne(optional = false)
    private CategoriasFAQ categoriaPregunta;

    public Faq() {
    }

    /**
     * @param idPregunta
     */
    public Faq( Integer idPregunta ) {
        this.idPregunta = idPregunta;
    }

    /**
     * @param idPregunta
     * @param tituloPregunta
     * @param respuestaPregunta
     * @param versionPregunta
     * @param visible
     * @param visitas
     */
    public Faq( Integer idPregunta, String tituloPregunta, String respuestaPregunta, int versionPregunta, boolean visible, long visitas ) {
        this.idPregunta = idPregunta;
        this.tituloPregunta = tituloPregunta;
        this.respuestaPregunta = respuestaPregunta;
        this.versionPregunta = versionPregunta;
        this.visible = visible;
        this.visitas = visitas;
    }

    /**
     * @return Integer
     */
    public Integer getIdPregunta() {
        return idPregunta;
    }

    /**
     * @param idPregunta
     */
    public void setIdPregunta( Integer idPregunta ) {
        this.idPregunta = idPregunta;
    }

    /**
     * @return String
     */
    public String getTituloPregunta() {
        return tituloPregunta;
    }

    /**
     * @param tituloPregunta
     */
    public void setTituloPregunta( String tituloPregunta ) {
        this.tituloPregunta = tituloPregunta;
    }

    /**
     * @return String
     */
    public String getRespuestaPregunta() {
        return respuestaPregunta;
    }

    /**
     * @param respuestaPregunta
     */
    public void setRespuestaPregunta( String respuestaPregunta ) {
        this.respuestaPregunta = respuestaPregunta;
    }

    /**
     * @return int
     */
    public int getVersionPregunta() {
        return versionPregunta;
    }

    /**
     * @param versionPregunta
     */
    public void setVersionPregunta( int versionPregunta ) {
        this.versionPregunta = versionPregunta;
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
     * @return long
     */
    public long getVisitas() {
        return visitas;
    }

    /**
     * @param visitas
     */
    public void setVisitas( long visitas ) {
        this.visitas = visitas;
    }

    /**
     * @return CategoriasFAQ
     */
    public CategoriasFAQ getCategoriaPregunta() {
        return categoriaPregunta;
    }

    /**
     * @param categoriaPregunta
     */
    public void setCategoriaPregunta( CategoriasFAQ categoriaPregunta ) {
        this.categoriaPregunta = categoriaPregunta;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idPregunta != null ? idPregunta.hashCode() : 0 );
        return hash;
    }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Faq ))
            return false;

        Faq other = ( Faq ) object;

        if (( this.idPregunta == null && other.idPregunta != null ) || ( this.idPregunta != null && !this.idPregunta.equals( other.idPregunta )))
            return false;

        return true;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "com.credibanco.Entity.Faq[ idPregunta=" + idPregunta + " ]";
    }
}