/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credibanco.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sami
 */
@Entity
@Table(name = "preguntas_val_tecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreguntasValTecnico.findAll", query = "SELECT p FROM PreguntasValTecnico p"),
    @NamedQuery(name = "PreguntasValTecnico.findByIdPregunta", query = "SELECT p FROM PreguntasValTecnico p WHERE p.idPregunta = :idPregunta"),
    @NamedQuery(name = "PreguntasValTecnico.findByTextoPregunta", query = "SELECT p FROM PreguntasValTecnico p WHERE p.textoPregunta = :textoPregunta"),
    @NamedQuery(name = "PreguntasValTecnico.findByVisible", query = "SELECT p FROM PreguntasValTecnico p WHERE p.visible = :visible")})
public class PreguntasValTecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Pregunta")
    private Integer idPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Texto_Pregunta")
    private String textoPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible")
    private boolean visible;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private Collection<RespuestasValTecnico> respuestasValTecnicoCollection;

    public PreguntasValTecnico() {
    }

    public PreguntasValTecnico(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public PreguntasValTecnico(Integer idPregunta, String textoPregunta, boolean visible) {
        this.idPregunta = idPregunta;
        this.textoPregunta = textoPregunta;
        this.visible = visible;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @XmlTransient
    public Collection<RespuestasValTecnico> getRespuestasValTecnicoCollection() {
        return respuestasValTecnicoCollection;
    }

    public void setRespuestasValTecnicoCollection(Collection<RespuestasValTecnico> respuestasValTecnicoCollection) {
        this.respuestasValTecnicoCollection = respuestasValTecnicoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntasValTecnico)) {
            return false;
        }
        PreguntasValTecnico other = (PreguntasValTecnico) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.credibanco.Entity.PreguntasValTecnico[ idPregunta=" + idPregunta + " ]";
    }
    
}
